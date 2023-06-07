package entity;
public class Cars extends EntityVin {
    private String BrandCar;
    private String BodyType;
    private String Transmission;
    public void setBrandCar(String BrandCar) {
        this.BrandCar = BrandCar;
    }
    public void setBodyType(String BodyType) {
        this.BodyType = BodyType;
    }
    public void setTransmission(String Transmission) {
        this.Transmission = Transmission;
    }
    public String getBrandCar() {
        return BrandCar;
    }
    public String getBodyType() {
        return BodyType;
    }
    public String getTransmission() {
        return Transmission;
    }
}