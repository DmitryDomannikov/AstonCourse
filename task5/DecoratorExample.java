package task5;

interface Text {
    String render();
}
class SimpleText implements Text { // компонент
    private String content;
    public SimpleText(String content) {
        this.content = content;
    }
    @Override
    public String render() {
        return content;
    }
}
abstract class TextDecorator implements Text { // декоратор
    protected Text decoratedText;
    public TextDecorator(Text decoratedText) {
        this.decoratedText = decoratedText;
    }
    @Override
    public String render() {
        return decoratedText.render();
    }
}
class BoldDecorator extends TextDecorator { // декоратор: Жирный текст
    public BoldDecorator(Text decoratedText) {
        super(decoratedText);
    }
    @Override
    public String render() {
        return "<b>" + super.render() + "</b>";
    }
}
class ItalicDecorator extends TextDecorator { // декоратор Курсив
    public ItalicDecorator(Text decoratedText) {
        super(decoratedText);
    }
    @Override
    public String render() {
        return "<i>" + super.render() + "</i>";
    }
}
public class DecoratorExample {
    public static void main(String[] args) {

        Text simpleText = new SimpleText("Hello, World!");    // Простой текст без форматирования
        System.out.println("Simple Text: " + simpleText.render());

        Text boldText = new BoldDecorator(simpleText);    // Добавляем жирное форматирование
        System.out.println("Bold Text: " + boldText.render());

        Text italicText = new ItalicDecorator(simpleText);        // Добавляем курсив
        System.out.println("Italic Text: " + italicText.render());

        Text boldItalicText = new ItalicDecorator(new BoldDecorator(simpleText));   // Комбинируем жирный и курсив
        System.out.println("Bold + Italic Text: " + boldItalicText.render());
    }
}