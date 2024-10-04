import java.util.Scanner;

public class StepTracker {
    private static final int DAYS_PER_MONTH = 30;

    Scanner scanner;
    MonthData[] monthToData = new MonthData[12];
    Converter converter = new Converter();
    int goalByStepsPerDay = 10000;

    StepTracker(Scanner scan) {
        scanner = scan;
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    void addNewNumberStepsPerDay() {
        printMonthQuestion();
        // ввод и проверка номера месяца
        int month = scanner.nextInt();

        if (month > 12 || month < 1) {
            printWrongMonthMessage();
            return;
        }

        System.out.println("Введите день от 1 до 30 (включительно)");
        // ввод и проверка дня
        int day = scanner.nextInt();

        if (day > 30 || day < 1) {
            System.out.println("Неверный номер дня. День должен быть от 1 до 30");
            return;
        }

        MonthData monthData = monthToData[month - 1];

        // проверка наличия данных за выбранный день
        if (monthData.days[day - 1] != 0) {
            System.out.println("Данные за этот день уже заполнены");
            return;
        }

        System.out.println("Введите количество шагов");
        // ввод и проверка количества шагов
        int stepsCount = scanner.nextInt();

        if (stepsCount < 0) {
            System.out.println("Указано неверное количество шагов. Количество шагов должно быть больше 0");
            return;
        }

        monthData.days[day - 1] = stepsCount;
    }

    void changeStepGoal() {
        System.out.println("Введите цель шагов на день");
        // ввод и проверка цели шагов
        int userGoal = scanner.nextInt();

        if (userGoal <= 0) {
            System.out.println("Цель должна быть больше нуля!");
            return;
        }
        goalByStepsPerDay = userGoal;
    }

    void printStatistic() {
        printMonthQuestion();
        int month = scanner.nextInt();

        if (month > 12 || month < 1) {
            printWrongMonthMessage();
            return;
        }

        MonthData monthData = monthToData[month - 1];
        int sumSteps = monthData.sumStepsFromMonth();
        System.out.println();
        System.out.println("Статистика за месяц:");
        monthData.printDaysAndStepsFromMonth();

        System.out.println();
        System.out.println("Общее количество шагов за месяц: " + sumSteps);
        System.out.println("Максимальное пройденное количество шагов за месяц: " + monthData.maxSteps());
        System.out.println("Среднее количество шагов: " + sumSteps / DAYS_PER_MONTH);
        System.out.println("Пройденная дистанция (в км): " + converter.convertToKm(sumSteps));
        System.out.println("Количество сожжённых килокалорий: " + converter.convertStepsToKilocalories(sumSteps));
        System.out.println("Лучшая серия: " + monthData.bestSeries(goalByStepsPerDay));
        System.out.println();
    }

    // вынесла в отдельную функцию, чтобы избежать дублирования кода
    void printMonthQuestion() {
        System.out.println("Введите номер месяца");
        System.out.println("1 - Январь, 2 - Февраль, 3 - Март, 4 - Апрель, 5 - Май, 6 - Июнь, 7 - Июль, 8 - Август, 9 - Сентябрь, 10 - Октябрь, 11 - Ноябрь, 12 - Декабрь");
    }

    void printWrongMonthMessage() {
            System.out.println("Неверный номер месяца. Месяц должен быть от 1 до 12");
    }
}
