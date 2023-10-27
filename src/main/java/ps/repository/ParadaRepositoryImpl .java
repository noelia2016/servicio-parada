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
        List<Parada> matchList = eList.stream().filter((e) -> (e.getLastName().contains(name)))
                .collect(Collectors.toList());

        return matchList;
    }

    @Override
    public List<Parada> getByDepartment(String department) {
        List<Parada> matchList = eList.stream().filter((e) -> (e.getDepartment().contains(department)))
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

    // se elimina una parada especificamente
    @Override
    public Parada update(Parada updatedParada, String id) {
        deleteById(id);
        Parada p = Parada.of(id, parada.getNombre(), parada.getLatitud(),
        parada.getLongitud());
        eList.add(p);
        return p;
    }

}