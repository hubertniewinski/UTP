/**
 *
 *  @author Niewiński Hubert S22740
 *
 */

package zad2;

public class Purchase {
    private String id;
    private String fullName;
    private String product;
    private float amount;
    private float cost;

    @Override
    public String toString() {
        return id + ";" + fullName + ";" + product + ";" + amount + ";" + cost;
    }

    public String toStringWithCost() {
        return toString() + " (koszt: " + getCost() + ")";
    }

    public Purchase(String input){
        String[] data = input.split(";");
        this.id = data[0];
        this.fullName = data[1];
        this.product = data[2];
        this.amount = Float.parseFloat(data[3]);
        this.cost = Float.parseFloat(data[4]);
    }

    public String getName(){
        return this.fullName.split(" ")[0];
    }

    public String getId() {
        return id;
    }

    public float getCost() {
        return cost * amount;
    }
}
