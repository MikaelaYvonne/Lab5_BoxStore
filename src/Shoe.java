/**
 * @author  Mikaela Yvonne Dacanay, Gregory McNutt, Thomas Wesley
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:
 */


public class Shoe extends ClothingItem {
    private String style; // sneakers, boots, heels etc

    public Shoe(int skuNumber, String itemName, double price, int itemCount,
                String size, String color, String style){
        super(skuNumber, itemName, price, itemCount, size, color);
        this.style = style;
    }

    public String getStyle() { return style; }

    @Override
    public String toString() {
        return super.toString() + ", Style: " + style;
    }

}
