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
