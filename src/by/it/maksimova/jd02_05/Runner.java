package by.it.maksimova.jd02_05;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;


public class Runner {
    public static void main(String[] args) {
        Language language = Language.INSTANCE;
        Locale.setDefault(Locale.ENGLISH);
        DateFormat dateToday = getDateToday(args, language);
        System.out.println(language.get(Messages.WELCOME));
        System.out.println(language.get(Messages.QUESTION));
        System.out.println(language.get(User.NAME));
        System.out.println(language.get(User.LAST_NAME));
        System.out.println(Objects.requireNonNull(dateToday).format(new Date()));
    }


    //Язык можно было переключать с клавиатуры командами ru be en.
    private static DateFormat getDateToday(String[] args, Language language) {
        Scanner scanner = new Scanner(System.in);
        DateFormat dateToday = null;
        switch (scanner.nextLine()) {
            case "ru":
                Locale ru = new Locale("ru", "RU");
                Language.INSTANCE.setLocale(ru);
                dateToday = DateFormat.getDateInstance(DateFormat.MEDIUM, ru);
                break;
            case "be":
                Locale be = new Locale("be", "BY");
                Language.INSTANCE.setLocale(be);
                dateToday = DateFormat.getDateInstance(DateFormat.MEDIUM, be);
                break;
            case "en":
                Locale en = new Locale("en", "EN");
                Language.INSTANCE.setLocale(en);
                dateToday = DateFormat.getDateInstance(DateFormat.MEDIUM, en);
                break;
            default:
                //Требуемый язык и страна должны сообщаться программе в качестве входных параметров.
                if (args.length == 2) {
                    language.setLocale(new Locale(args[0], args[1]));
                    dateToday = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale(args[0], args[1]));
                }
        }
        return dateToday;
    }
}
