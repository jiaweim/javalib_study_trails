package tutorial.lib.guava.samples;

import java.util.Optional;

public class ToOrder
{
    private Integer v1;
    private String v2;
    private Double v3;

    public ToOrder(Integer v1, String v2, Double v3)
    {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }

    public Optional<Integer> getV1()
    {
        return Optional.ofNullable(v1);
    }

    public String getV2()
    {
        return v2;
    }

    public Double getV3()
    {
        return v3;
    }

    @Override
    public String toString()
    {
        return "ToOrder{" +
                "v1=" + v1 +
                ", v2='" + v2 + '\'' +
                ", v3=" + v3 +
                '}';
    }
}