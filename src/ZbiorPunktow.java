import java.util.HashSet;
import java.util.Set;

public class ZbiorPunktow<T>
{
    Set<T> zbiorPunktow;

    public ZbiorPunktow() {
        zbiorPunktow = new HashSet<>();
    }

    public void dodajPunkt(T point)
    {
        zbiorPunktow.add(point);
    }

    public void usunPunkt(T point)
    {
        zbiorPunktow.remove(point);
    }

    public boolean czyPunktJestWZbiorze(T point)
    {
        return zbiorPunktow.contains(point);
    }

    public int mocZbioru()
    {
        return zbiorPunktow.size();
    }
}