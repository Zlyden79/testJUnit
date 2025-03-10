public class PasswordChecker {
    private int minLegth; // минимальная длина пароля
    private int maxRepeats; //максимальное количество повторений символа подряд

    //конструктор по умолчанию без параметров,
    // потому что параметры выставляются сеттерами.

    public void setMinLegth(int minLegth) throws IllegalArgumentException {
        if (minLegth < 2) {
            throw new IllegalArgumentException("Мин. длина пароля не может быть меньше 2");
        } else {
            this.minLegth = minLegth;
        }
    }

    public void setMaxRepeats(int maxRepeats) throws IllegalArgumentException {
        if (maxRepeats < 1) {
            throw new IllegalArgumentException("Макс. допустимое количество повторений символа подряд не может быть меньше 1");
        } else {
            this.maxRepeats = maxRepeats;
        }
    }

    //метод возвращает максимальное количество повторений символа подряд в пароле
    public static int calcMaxRepeats(String password) {
        // "Обнулённый" счётчик = 1, ведь не бывает ноль подряд одинаковых символов.
        int count = 1;
        int maxCount = 1;
        for (int i = 0; i < password.length() - 1; i++) {
            if (password.charAt(i) == password.charAt(i + 1)) {
                count++;
            } else {
                count = 1;
            }
            if (count > maxCount) {
                maxCount = count;
            }
        }
        return maxCount;
    }

    boolean verify(String password) throws IllegalStateException {
        //TODO вычислить длину пароля и максимальное количество
        // повторений символа подряд в пароле.
        //TODO сравнить с параметрами в полях класса, вернуть вердикт
        if (minLegth < 2 || maxRepeats < 1) {
            throw new IllegalStateException("Параметры проверки не определены либо не верны");
        }

        if (password.length() < minLegth || calcMaxRepeats(password) > maxRepeats) {
            return false;
        } else {
            return true;
        }
    }
}
