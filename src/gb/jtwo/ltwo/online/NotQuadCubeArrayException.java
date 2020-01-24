package gb.jtwo.ltwo.online;

import java.io.IOException;

public class NotQuadCubeArrayException extends IOException {
    private int maxel = 0, minel = 0;

    public NotQuadCubeArrayException(int maxel, int minel, int maxstr) {
        super("\nВыявлено несоблюдение условия размерности массива,\n текущий размер массива " + maxstr + "x" + maxel + "\nминимальная длинна строки " + minel);
        this.maxel = maxel;
        this.minel = minel;
    }

    public int getMaxel() {
        return maxel;
    }

    public int getMinel() {
        return minel;
    }
}
