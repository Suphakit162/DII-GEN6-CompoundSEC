abstract class Sensor {

    public abstract double readValue();
}


///ค่าLux แสงต่ำกว่า 10000-20000 ควรรับเเสง เเต่ถ้า 40000 - 50000 ควรปิด

class LightSensor extends Sensor {
    private double currentLightLevel;

    public LightSensor() {

        this.currentLightLevel = 0;
    }

    @Override
    public double readValue() {
        return this.currentLightLevel;
    }

}
