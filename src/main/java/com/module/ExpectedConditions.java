package com.module;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import com.google.common.base.Joiner;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ExpectedConditions {
    private static final Logger log = Logger.getLogger(ExpectedConditions.class.getName());

    private ExpectedConditions() {
    }

    public static ExpectedCondition<Boolean> titleIs(final String title) {
        return new ExpectedCondition<Boolean>() {
            private String currentTitle = "";

            public Boolean apply(WebDriver driver) {
                this.currentTitle = driver.getTitle();
                return title.equals(this.currentTitle);
            }

            public String toString() {
                return String.format("title to be \"%s\". Current title: \"%s\"", title, this.currentTitle);
            }
        };
    }

    public static ExpectedCondition<Boolean> titleContains(final String title) {
        return new ExpectedCondition<Boolean>() {
            private String currentTitle = "";

            public Boolean apply(WebDriver driver) {
                this.currentTitle = driver.getTitle();
                return this.currentTitle != null && this.currentTitle.contains(title);
            }

            public String toString() {
                return String.format("title to contain \"%s\". Current title: \"%s\"", title, this.currentTitle);
            }
        };
    }

    public static ExpectedCondition<Boolean> urlToBe(final String url) {
        return new ExpectedCondition<Boolean>() {
            private String currentUrl = "";

            public Boolean apply(WebDriver driver) {
                this.currentUrl = driver.getCurrentUrl();
                return this.currentUrl != null && this.currentUrl.equals(url);
            }

            public String toString() {
                return String.format("url to be \"%s\". Current url: \"%s\"", url, this.currentUrl);
            }
        };
    }

    public static ExpectedCondition<Boolean> urlContains(final String fraction) {
        return new ExpectedCondition<Boolean>() {
            private String currentUrl = "";

            public Boolean apply(WebDriver driver) {
                this.currentUrl = driver.getCurrentUrl();
                return this.currentUrl != null && this.currentUrl.contains(fraction);
            }

            public String toString() {
                return String.format("url to contain \"%s\". Current url: \"%s\"", fraction, this.currentUrl);
            }
        };
    }

    public static ExpectedCondition<Boolean> urlMatches(final String regex) {
        return new ExpectedCondition<Boolean>() {
            private String currentUrl;
            private Pattern pattern;
            private Matcher matcher;

            public Boolean apply(WebDriver driver) {
                this.currentUrl = driver.getCurrentUrl();
                this.pattern = Pattern.compile(regex);
                this.matcher = this.pattern.matcher(this.currentUrl);
                return this.matcher.find();
            }

            public String toString() {
                return String.format("url to match the regex \"%s\". Current url: \"%s\"", regex, this.currentUrl);
            }
        };
    }

    public static ExpectedCondition<WebElement> presenceOfElementLocated(final By locator) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }

            public String toString() {
                return "presence of element located by: " + locator;
            }
        };
    }

    public static ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                try {
                    return ExpectedConditions.elementIfVisible(driver.findElement(locator));
                } catch (StaleElementReferenceException var3) {
                    return null;
                }
            }

            public String toString() {
                return "visibility of element located by " + locator;
            }
        };
    }

    public static ExpectedCondition<List<WebElement>> visibilityOfAllElementsLocatedBy(final By locator) {
        return new ExpectedCondition<List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                List<WebElement> elements = driver.findElements(locator);
                Iterator var3 = elements.iterator();

                WebElement element;
                do {
                    if (!var3.hasNext()) {
                        return elements.size() > 0 ? elements : null;
                    }

                    element = (WebElement)var3.next();
                } while(element.isDisplayed());

                return null;
            }

            public String toString() {
                return "visibility of all elements located by " + locator;
            }
        };
    }

    public static ExpectedCondition<List<WebElement>> visibilityOfAllElements(WebElement... elements) {
        return visibilityOfAllElements(Arrays.asList(elements));
    }

    public static ExpectedCondition<List<WebElement>> visibilityOfAllElements(final List<WebElement> elements) {
        return new ExpectedCondition<List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                Iterator var2 = elements.iterator();

                WebElement element;
                do {
                    if (!var2.hasNext()) {
                        return elements.size() > 0 ? elements : null;
                    }

                    element = (WebElement)var2.next();
                } while(element.isDisplayed());

                return null;
            }

            public String toString() {
                return "visibility of all " + elements;
            }
        };
    }

    public static ExpectedCondition<WebElement> visibilityOf(final WebElement element) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                return ExpectedConditions.elementIfVisible(element);
            }

            public String toString() {
                return "visibility of " + element;
            }
        };
    }

    private static WebElement elementIfVisible(WebElement element) {
        return element.isDisplayed() ? element : null;
    }

    public static ExpectedCondition<List<WebElement>> presenceOfAllElementsLocatedBy(final By locator) {
        return new ExpectedCondition<List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                List<WebElement> elements = driver.findElements(locator);
                return elements.size() > 0 ? elements : null;
            }

            public String toString() {
                return "presence of any elements located by " + locator;
            }
        };
    }

    public static ExpectedCondition<Boolean> textToBePresentInElement(final WebElement element, final String text) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = element.getText();
                    return elementText.contains(text);
                } catch (StaleElementReferenceException var3) {
                    return null;
                }
            }

            public String toString() {
                return String.format("text ('%s') to be present in element %s", text, element);
            }
        };
    }

    public static ExpectedCondition<Boolean> textToBePresentInElementLocated(final By locator, final String text) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = driver.findElement(locator).getText();
                    return elementText.contains(text);
                } catch (StaleElementReferenceException var3) {
                    return null;
                }
            }

            public String toString() {
                return String.format("text ('%s') to be present in element found by %s", text, locator);
            }
        };
    }

    public static ExpectedCondition<Boolean> textToBePresentInElementValue(final WebElement element, final String text) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = element.getAttribute("value");
                    return elementText != null ? elementText.contains(text) : false;
                } catch (StaleElementReferenceException var3) {
                    return null;
                }
            }

            public String toString() {
                return String.format("text ('%s') to be the value of element %s", text, element);
            }
        };
    }

    public static ExpectedCondition<Boolean> textToBePresentInElementValue(final By locator, final String text) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = driver.findElement(locator).getAttribute("value");
                    return elementText != null ? elementText.contains(text) : false;
                } catch (StaleElementReferenceException var3) {
                    return null;
                }
            }

            public String toString() {
                return String.format("text ('%s') to be the value of element located by %s", text, locator);
            }
        };
    }

    public static ExpectedCondition<WebDriver> frameToBeAvailableAndSwitchToIt(final String frameLocator) {
        return new ExpectedCondition<WebDriver>() {
            public WebDriver apply(WebDriver driver) {
                try {
                    return driver.switchTo().frame(frameLocator);
                } catch (NoSuchFrameException var3) {
                    return null;
                }
            }

            public String toString() {
                return "frame to be available: " + frameLocator;
            }
        };
    }

    public static ExpectedCondition<WebDriver> frameToBeAvailableAndSwitchToIt(final By locator) {
        return new ExpectedCondition<WebDriver>() {
            public WebDriver apply(WebDriver driver) {
                try {
                    return driver.switchTo().frame(driver.findElement(locator));
                } catch (NoSuchFrameException var3) {
                    return null;
                }
            }

            public String toString() {
                return "frame to be available: " + locator;
            }
        };
    }

    public static ExpectedCondition<WebDriver> frameToBeAvailableAndSwitchToIt(final int frameLocator) {
        return new ExpectedCondition<WebDriver>() {
            public WebDriver apply(WebDriver driver) {
                try {
                    return driver.switchTo().frame(frameLocator);
                } catch (NoSuchFrameException var3) {
                    return null;
                }
            }

            public String toString() {
                return "frame to be available: " + frameLocator;
            }
        };
    }

    public static ExpectedCondition<WebDriver> frameToBeAvailableAndSwitchToIt(final WebElement frameLocator) {
        return new ExpectedCondition<WebDriver>() {
            public WebDriver apply(WebDriver driver) {
                try {
                    return driver.switchTo().frame(frameLocator);
                } catch (NoSuchFrameException var3) {
                    return null;
                }
            }

            public String toString() {
                return "frame to be available: " + frameLocator;
            }
        };
    }

    public static ExpectedCondition<Boolean> invisibilityOfElementLocated(final By locator) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return !driver.findElement(locator).isDisplayed();
                } catch (NoSuchElementException var3) {
                    return true;
                } catch (StaleElementReferenceException var4) {
                    return true;
                }
            }

            public String toString() {
                return "element to no longer be visible: " + locator;
            }
        };
    }

    public static ExpectedCondition<Boolean> invisibilityOfElementWithText(final By locator, final String text) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return !driver.findElement(locator).getText().equals(text);
                } catch (NoSuchElementException var3) {
                    return true;
                } catch (StaleElementReferenceException var4) {
                    return true;
                }
            }

            public String toString() {
                return String.format("element containing '%s' to no longer be visible: %s", text, locator);
            }
        };
    }

    public static ExpectedCondition<WebElement> elementToBeClickable(final By locator) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = (WebElement)ExpectedConditions.visibilityOfElementLocated(locator).apply(driver);

                try {
                    return element != null && element.isEnabled() ? element : null;
                } catch (StaleElementReferenceException var4) {
                    return null;
                }
            }

            public String toString() {
                return "element to be clickable: " + locator;
            }
        };
    }

    public static ExpectedCondition<WebElement> elementToBeClickable(final WebElement element) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement visibleElement = (WebElement)ExpectedConditions.visibilityOf(element).apply(driver);

                try {
                    return visibleElement != null && visibleElement.isEnabled() ? visibleElement : null;
                } catch (StaleElementReferenceException var4) {
                    return null;
                }
            }

            public String toString() {
                return "element to be clickable: " + element;
            }
        };
    }

    public static ExpectedCondition<Boolean> stalenessOf(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver ignored) {
                try {
                    element.isEnabled();
                    return false;
                } catch (StaleElementReferenceException var3) {
                    return true;
                }
            }

            public String toString() {
                return String.format("element (%s) to become stale", element);
            }
        };
    }

    public static <T> ExpectedCondition<T> refreshed(final ExpectedCondition<T> condition) {
        return new ExpectedCondition<T>() {
            public T apply(WebDriver driver) {
                try {
                    return condition.apply(driver);
                } catch (StaleElementReferenceException var3) {
                    return null;
                }
            }

            public String toString() {
                return String.format("condition (%s) to be refreshed", condition);
            }
        };
    }

    public static ExpectedCondition<Boolean> elementToBeSelected(WebElement element) {
        return elementSelectionStateToBe(element, true);
    }

    public static ExpectedCondition<Boolean> elementSelectionStateToBe(final WebElement element, final boolean selected) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return element.isSelected() == selected;
            }

            public String toString() {
                return String.format("element (%s) to %sbe selected", element, selected ? "" : "not ");
            }
        };
    }

    public static ExpectedCondition<Boolean> elementToBeSelected(By locator) {
        return elementSelectionStateToBe(locator, true);
    }

    public static ExpectedCondition<Boolean> elementSelectionStateToBe(final By locator, final boolean selected) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    WebElement element = driver.findElement(locator);
                    return element.isSelected() == selected;
                } catch (StaleElementReferenceException var3) {
                    return null;
                }
            }

            public String toString() {
                return String.format("element found by %s to %sbe selected", locator, selected ? "" : "not ");
            }
        };
    }

    public static ExpectedCondition<Alert> alertIsPresent() {
        return new ExpectedCondition<Alert>() {
            public Alert apply(WebDriver driver) {
                try {
                    return driver.switchTo().alert();
                } catch (NoAlertPresentException var3) {
                    return null;
                }
            }

            public String toString() {
                return "alert to be present";
            }
        };
    }

    public static ExpectedCondition<Boolean> numberOfWindowsToBe(final int expectedNumberOfWindows) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return driver.getWindowHandles().size() == expectedNumberOfWindows;
                } catch (WebDriverException var3) {
                    return null;
                }
            }

            public String toString() {
                return "number of open windows to be " + expectedNumberOfWindows;
            }
        };
    }

    public static ExpectedCondition<Boolean> not(final ExpectedCondition<?> condition) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                Object result = condition.apply(driver);
                return result == null || result.equals(Boolean.FALSE);
            }

            public String toString() {
                return "condition to not be valid: " + condition;
            }
        };
    }

    public static ExpectedCondition<Boolean> attributeToBe(final By locator, final String attribute, final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            public Boolean apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                this.currentValue = element.getAttribute(attribute);
                if (this.currentValue == null || this.currentValue.isEmpty()) {
                    this.currentValue = element.getCssValue(attribute);
                }

                return value.equals(this.currentValue);
            }

            public String toString() {
                return String.format("element found by %s to have value \"%s\". Current value: \"%s\"", locator, value, this.currentValue);
            }
        };
    }

    public static ExpectedCondition<Boolean> textToBe(final By locator, final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            public Boolean apply(WebDriver driver) {
                try {
                    this.currentValue = driver.findElement(locator).getText();
                    return this.currentValue.equals(value);
                } catch (Exception var3) {
                    return false;
                }
            }

            public String toString() {
                return String.format("element found by %s to have text \"%s\". Current text: \"%s\"", locator, value, this.currentValue);
            }
        };
    }

    public static ExpectedCondition<Boolean> textMatches(final By locator, final Pattern pattern) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            public Boolean apply(WebDriver driver) {
                try {
                    this.currentValue = driver.findElement(locator).getText();
                    return pattern.matcher(this.currentValue).find();
                } catch (Exception var3) {
                    return false;
                }
            }

            public String toString() {
                return String.format("text found by %s to match pattern \"%s\". Current text: \"%s\"", locator, pattern.pattern(), this.currentValue);
            }
        };
    }

    public static ExpectedCondition<List<WebElement>> numberOfElementsToBeMoreThan(final By locator, final Integer number) {
        return new ExpectedCondition<List<WebElement>>() {
            private Integer currentNumber = 0;

            public List<WebElement> apply(WebDriver webDriver) {
                List<WebElement> elements = webDriver.findElements(locator);
                this.currentNumber = elements.size();
                return this.currentNumber > number ? elements : null;
            }

            public String toString() {
                return String.format("number of elements found by %s to be more than \"%s\". Current number: \"%s\"", locator, number, this.currentNumber);
            }
        };
    }

    public static ExpectedCondition<List<WebElement>> numberOfElementsToBeLessThan(final By locator, final Integer number) {
        return new ExpectedCondition<List<WebElement>>() {
            private Integer currentNumber = 0;

            public List<WebElement> apply(WebDriver webDriver) {
                List<WebElement> elements = webDriver.findElements(locator);
                this.currentNumber = elements.size();
                return this.currentNumber < number ? elements : null;
            }

            public String toString() {
                return String.format("number of elements found by %s to be less than \"%s\". Current number: \"%s\"", locator, number, this.currentNumber);
            }
        };
    }

    public static ExpectedCondition<List<WebElement>> numberOfElementsToBe(final By locator, final Integer number) {
        return new ExpectedCondition<List<WebElement>>() {
            private Integer currentNumber = 0;

            public List<WebElement> apply(WebDriver webDriver) {
                List<WebElement> elements = webDriver.findElements(locator);
                this.currentNumber = elements.size();
                return this.currentNumber.equals(number) ? elements : null;
            }

            public String toString() {
                return String.format("number of elements found by %s to be \"%s\". Current number: \"%s\"", locator, number, this.currentNumber);
            }
        };
    }

    public static ExpectedCondition<Boolean> attributeToBe(final WebElement element, final String attribute, final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            public Boolean apply(WebDriver driver) {
                this.currentValue = element.getAttribute(attribute);
                if (this.currentValue == null || this.currentValue.isEmpty()) {
                    this.currentValue = element.getCssValue(attribute);
                }

                return value.equals(this.currentValue);
            }

            public String toString() {
                return String.format(attribute + " to be \"%s\". Current " + attribute + ": \"%s\"", value, this.currentValue);
            }
        };
    }

    public static ExpectedCondition<Boolean> attributeContains(final WebElement element, final String attribute, final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            public Boolean apply(WebDriver driver) {
                return (Boolean)ExpectedConditions.getAttributeOrCssValue(element, attribute).map((seen) -> {
                    return seen.contains(value);
                }).orElse(false);
            }

            public String toString() {
                return String.format("value to contain \"%s\". Current value: \"%s\"", value, this.currentValue);
            }
        };
    }

    public static ExpectedCondition<Boolean> attributeContains(final By locator, final String attribute, final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            public Boolean apply(WebDriver driver) {
                return (Boolean)ExpectedConditions.getAttributeOrCssValue(driver.findElement(locator), attribute).map((seen) -> {
                    return seen.contains(value);
                }).orElse(false);
            }

            public String toString() {
                return String.format("value found by %s to contain \"%s\". Current value: \"%s\"", locator, value, this.currentValue);
            }
        };
    }

    public static ExpectedCondition<Boolean> attributeToBeNotEmpty(WebElement element, String attribute) {
        return (driver) -> {
            return getAttributeOrCssValue(element, attribute).isPresent();
        };
    }

    private static Optional<String> getAttributeOrCssValue(WebElement element, String name) {
        String value = element.getAttribute(name);
        if (value == null || value.isEmpty()) {
            value = element.getCssValue(name);
        }

        return value != null && !value.isEmpty() ? Optional.of(value) : Optional.empty();
    }

    public static ExpectedCondition<List<WebElement>> visibilityOfNestedElementsLocatedBy(final By parent, final By childLocator) {
        return new ExpectedCondition<List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                WebElement current = driver.findElement(parent);
                List<WebElement> allChildren = current.findElements(childLocator);
                return !allChildren.isEmpty() && ((WebElement)allChildren.get(0)).isDisplayed() ? allChildren : null;
            }

            public String toString() {
                return String.format("visibility of elements located by %s -> %s", parent, childLocator);
            }
        };
    }

    public static ExpectedCondition<List<WebElement>> visibilityOfNestedElementsLocatedBy(final WebElement element, final By childLocator) {
        return new ExpectedCondition<List<WebElement>>() {
            public List<WebElement> apply(WebDriver webDriver) {
                List<WebElement> allChildren = element.findElements(childLocator);
                return !allChildren.isEmpty() && ((WebElement)allChildren.get(0)).isDisplayed() ? allChildren : null;
            }

            public String toString() {
                return String.format("visibility of element located by %s -> %s", element, childLocator);
            }
        };
    }

    public static ExpectedCondition<WebElement> presenceOfNestedElementLocatedBy(final By locator, final By childLocator) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(locator).findElement(childLocator);
            }

            public String toString() {
                return String.format("visibility of element located by %s -> %s", locator, childLocator);
            }
        };
    }

    public static ExpectedCondition<WebElement> presenceOfNestedElementLocatedBy(final WebElement element, final By childLocator) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return element.findElement(childLocator);
            }

            public String toString() {
                return String.format("visibility of element located by %s", childLocator);
            }
        };
    }

    public static ExpectedCondition<List<WebElement>> presenceOfNestedElementsLocatedBy(final By parent, final By childLocator) {
        return new ExpectedCondition<List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                List<WebElement> allChildren = driver.findElement(parent).findElements(childLocator);
                return allChildren.isEmpty() ? null : allChildren;
            }

            public String toString() {
                return String.format("visibility of element located by %s -> %s", parent, childLocator);
            }
        };
    }

    public static ExpectedCondition<Boolean> invisibilityOfAllElements(WebElement... elements) {
        return invisibilityOfAllElements(Arrays.asList(elements));
    }

    public static ExpectedCondition<Boolean> invisibilityOfAllElements(final List<WebElement> elements) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return elements.stream().allMatch((x$0) -> {
                    return ExpectedConditions.isInvisible(x$0);
                });
            }

            public String toString() {
                return "invisibility of all elements " + elements;
            }
        };
    }

    public static ExpectedCondition<Boolean> invisibilityOf(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return ExpectedConditions.isInvisible(element);
            }

            public String toString() {
                return "invisibility of " + element;
            }
        };
    }

    private static boolean isInvisible(WebElement element) {
        try {
            return !element.isDisplayed();
        } catch (StaleElementReferenceException var2) {
            return true;
        }
    }

    public static ExpectedCondition<Boolean> or(final ExpectedCondition<?>... conditions) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                RuntimeException lastException = null;
                ExpectedCondition[] var3 = conditions;
                int var4 = var3.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    ExpectedCondition<?> condition = var3[var5];

                    try {
                        Object result = condition.apply(driver);
                        if (result != null) {
                            if (!(result instanceof Boolean)) {
                                return true;
                            }

                            if (Boolean.TRUE.equals(result)) {
                                return true;
                            }
                        }
                    } catch (RuntimeException var8) {
                        lastException = var8;
                    }
                }

                if (lastException != null) {
                    throw lastException;
                } else {
                    return false;
                }
            }

            public String toString() {
                StringBuilder message = new StringBuilder("at least one condition to be valid: ");
                Joiner.on(" || ").appendTo(message, conditions);
                return message.toString();
            }
        };
    }

    public static ExpectedCondition<Boolean> and(final ExpectedCondition<?>... conditions) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                ExpectedCondition[] var2 = conditions;
                int var3 = var2.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    ExpectedCondition<?> condition = var2[var4];
                    Object result = condition.apply(driver);
                    if (result instanceof Boolean && Boolean.FALSE.equals(result)) {
                        return false;
                    }

                    if (result == null) {
                        return false;
                    }
                }

                return true;
            }

            public String toString() {
                StringBuilder message = new StringBuilder("all conditions to be valid: ");
                Joiner.on(" && ").appendTo(message, conditions);
                return message.toString();
            }
        };
    }

    public static ExpectedCondition<Boolean> javaScriptThrowsNoExceptions(final String javaScript) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    ((JavascriptExecutor)driver).executeScript(javaScript, new Object[0]);
                    return true;
                } catch (WebDriverException var3) {
                    return false;
                }
            }

            public String toString() {
                return String.format("js %s to be executable", javaScript);
            }
        };
    }

    public static ExpectedCondition<Object> jsReturnsValue(final String javaScript) {
        return new ExpectedCondition<Object>() {
            public Object apply(WebDriver driver) {
                try {
                    Object value = ((JavascriptExecutor)driver).executeScript(javaScript, new Object[0]);
                    if (value instanceof List) {
                        return ((List)value).isEmpty() ? null : value;
                    } else if (value instanceof String) {
                        return ((String)value).isEmpty() ? null : value;
                    } else {
                        return value;
                    }
                } catch (WebDriverException var3) {
                    return null;
                }
            }

            public String toString() {
                return String.format("js %s to be executable", javaScript);
            }
        };
    }
}

