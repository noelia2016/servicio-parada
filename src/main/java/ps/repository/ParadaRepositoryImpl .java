public final class ParadaRepositoryImpl implements ParadaRepository {

    private final CopyOnWriteArrayList<Parada> List = new ArrayList<Parada>();

    public ParadaRepositoryImpl() {
        JsonbConfig config = new JsonbConfig().withFormatting(Boolean.TRUE);

        Jsonb jsonb = JsonbBuilder.create(config);

        eList.addAll(jsonb.fromJson(ParadaRepositoryImpl.class.getResourceAsStream("/parada.json"),
                new ArrayList<Parada>() {
                }.getClass().getGenericSuperclass()));
    }

    @Override
    public List<Parada> getByLastName(String name) {
        List<Parada> matchList = eList.stream().filter((e) -> (e.getNombre().contains(name)))
                .collect(Collectors.toList());

        return matchList;
    }

    @Override
    public List<Parada> getAll() {
        return eList;
    }

    // busca en la lista y devuelve null si no se encuentra ningún ID coincidente.
    @Override
    public Parada getById(String id) {
        Parada parada;
        parada = eList.stream().filter(e -> e.getId().equals(id)).findFirst().get();
        return parada;
    }

    // guarda una nueva parada para los monopatines
    @Override
    public Parada save(Parada parada) {
        Parada nextParada = Parada.of(null, parada.getNombre(), parada.getLatitud(),
            parada.getLongitud()); 
        eList.add(nextParada);
        return nextParada;
    }

    // actualiza un objeto existente suprimiendo el objeto antiguo y creando un nuevo objeto con los nuevos datos. 
    @Override
    public Parada update(Parada updatedParada, String id) {
        deleteById(id);
        Parada p = Parada.of(id, parada.getNombre(), parada.getLatitud(),
        parada.getLongitud());
        eList.add(p);
        return p;
    }

    //El método sólo busca el ID de objetos y, a continuación, obtiene el índice del elemento de lista. El índice de lista se utiliza para suprimir el elemento de lista.
    @Override
    public void deleteById(String id) {
        int matchIndex;
        matchIndex = eList.stream().filter(e -> e.getId().equals(id)).findFirst().map(e -> eList.indexOf(e)).get();
        eList.remove(matchIndex);
    }

}