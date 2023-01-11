import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CiagPunktow<T> implements Iterable<T>
{
    private List<T> ciagPunktow = new ArrayList<>();
    public void usunPunkt(T point)
    {
        if (ciagPunktow.contains(point))
        {
            ciagPunktow.remove(point);
        }
    }

    public boolean czyPunktJestWCiagu(T point)
    {
        return ciagPunktow.contains(point);
    }

    public void dodajPunkt(T point)
    {
        ciagPunktow.add(point);
    }

    public T podajPunkt(int i)
    {
        return ciagPunktow.get(i);
    }

    public int dlugoscCiagu()
    {
        return ciagPunktow.size();
    }
    @Override
    public Iterator<T> iterator() {
        return ciagPunktow.iterator();
    }
}