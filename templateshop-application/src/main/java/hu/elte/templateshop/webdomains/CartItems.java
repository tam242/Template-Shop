package hu.elte.templateshop.webdomains;

import java.util.List;

public class CartItems {
    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    private List<String> items;
}
