package java.jdbs;

public class Space
{
    private int ID;
    private String Name;
    private float Diameter_km;

    public Space(){}
    public Space(int id, String name, float diameter_km) {
        this.ID = id;
        this.Name = name;
        this.Diameter_km = diameter_km;
    }
    @Override
    public String toString()
    {
        return "Space: [" + ID + "\t" + Name + "\t" + Diameter_km;
    }
}