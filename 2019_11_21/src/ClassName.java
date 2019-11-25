public interface ClassName {
    String getClassName();
}

class Company implements ClassName {
    @Override
    public String getClassName() {
        return name;
    }

    String name;

    public Company(String name) {
        this.name = name;
    }
}
