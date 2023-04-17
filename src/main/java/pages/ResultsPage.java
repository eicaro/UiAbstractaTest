package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage {
    private WebDriver driver;
    private By resultsLocator = By.cssSelector("#root-app > div > div.ui-search-main.ui-search-main--without-header.ui-search-main--only-products.shops__search-main > section > ol");

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getCamisetasLinks() {
        WebElement results = driver.findElement(resultsLocator);
        List<WebElement> links = results.findElements(By.tagName("a"));
        List<WebElement> linksCamisetas = new ArrayList<>();

        for (WebElement link : links) {
            String textoLink = link.getText();
            String urlLink = link.getAttribute("href");
            if (textoLink.contains("Camiseta") && urlLink != null) {
                linksCamisetas.add(link);
            }
        }
        return linksCamisetas;
    }

    public List<String> getPrecios(int posicionLink) {
        List<String> precios = new ArrayList<String>();
        for (int i = 1; i <= posicionLink; i++) {
            WebElement precio = driver.findElement(By.cssSelector("#root-app > div > div.ui-search-main.ui-search-main--without-header.ui-search-main--only-products.shops__search-main > section > ol > li:nth-child(" + i + ") > div > div > div.ui-search-result__content-wrapper.shops__result-content-wrapper > div.ui-search-result__content-columns.shops__content-columns > div.ui-search-result__content-column.ui-search-result__content-column--left.shops__content-columns-left > div > div > div > div > span.price-tag.ui-search-price__part.shops__price-part > span.price-tag-amount"));
            String precioReal = precio.getText();
            precios.add(precioReal);
        }
        return precios;
    }

    public void acceptCookies() {
        WebElement cookieBanner = driver.findElement(By.cssSelector("body > div:nth-child(5) > div.cookie-consent-banner-opt-out > div.cookie-consent-banner-opt-out__actions > button.cookie-consent-banner-opt-out__action.cookie-consent-banner-opt-out__action--primary.cookie-consent-banner-opt-out__action--key-accept"));
        cookieBanner.click();
    }

    public void goToNextPage() {
        WebElement nextPage = driver.findElement(By.cssSelector("#root-app > div > div.ui-search-main.ui-search-main--without-header.ui-search-main--only-products.shops__search-main > section > div.ui-search-pagination.shops__pagination-content > ul > li.andes-pagination__button.andes-pagination__button--next.shops__pagination-button"));
        nextPage.click();
    }

    // En tu clase ResultsPage

    public List<Product> createProductList(List<Product> existingProducts, List<WebElement> linksCamisetas, List<String> precios) {
        if (existingProducts == null) {
            existingProducts = new ArrayList<>();
        }

        if (linksCamisetas.size() == precios.size()) {
            for (int i = 0; i < linksCamisetas.size(); i++) {
                WebElement linkCamiseta = linksCamisetas.get(i);
                String textoLinkCamiseta = linkCamiseta.getText();
                String urlLinkCamiseta = linkCamiseta.getAttribute("href");

                String precio = precios.get(i);
                precio = precio.replaceAll("\\n", " ");

                Product producto = new Product(textoLinkCamiseta, urlLinkCamiseta, precio);
                existingProducts.add(producto);
            }
        } else {
            System.out.println("Las listas tienen diferentes tamaños. No es posible combinarlas.");
        }

        return existingProducts;
    }


}
