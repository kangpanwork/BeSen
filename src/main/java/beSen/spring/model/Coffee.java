package beSen.spring.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author 康盼Java开发工程师
 */
public class Coffee extends BaseEntity {
    //只能作用在 String 上,不能为null, 而且调用trim()后, 长度必须大于0

    @NotBlank(message = "name can not be null")
    private String name;

    @Max(value = 106, message = "state is not in coffeeState")
    @Min(value = 101, message = "state is not in coffeeState")
    private int state;

    @Min(value = 0, message = "price must be higher or equal to zero")
    private double price;

    @Size(min = 1,max = 200,message = "coffeeDesc length must in 1~200 size")
    private String coffeeDesc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCoffeeDesc() {
        return coffeeDesc;
    }

    public void setCoffeeDesc(String coffeeDesc) {
        this.coffeeDesc = coffeeDesc;
    }

    public Coffee() {
    }

    public Coffee(@NotBlank(message = "name can not be null") String name,
                  @Max(value = 106, message = "state is not in coffeeState")
                  @Min(value = 101, message = "state is not in coffeeState") int state,
                  @Min(value = 0, message = "price must be higher or equal to zero") double price,
                  @Size(min = 1, max = 200, message = "coffeeDesc length must in 1~200 size") String coffeeDesc) {
        this.name = name;
        this.state = state;
        this.price = price;
        this.coffeeDesc = coffeeDesc;
    }
}
