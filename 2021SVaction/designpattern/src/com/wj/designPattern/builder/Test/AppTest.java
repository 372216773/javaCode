package com.wj.designPattern.builder.Test;

//一个电脑的基本配件
class Computer {
    private String cpu;
    private String gpu;
    private String memory;
    private String hd;

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", gpu='" + gpu + '\'' +
                ", memory='" + memory + '\'' +
                ", hd='" + hd + '\'' +
                '}';
    }
}

//制定一套流程（保证不会有差漏）
interface ComputerBuilder {
    void setCpu();

    void setGpu();

    void setMemory();

    void setHd();

    Computer build();
}

//高配工厂（提供配件）
class AdvancedComputerBuilder implements ComputerBuilder {

    Computer computer = new Computer();

    @Override
    public void setCpu() {
        computer.setCpu("cpu_1");
    }

    @Override
    public void setGpu() {
        computer.setGpu("gpu_1");
    }

    @Override
    public void setMemory() {
        computer.setMemory("memory_1");
    }

    @Override
    public void setHd() {
        computer.setHd("hd_1");
    }

    @Override
    public Computer build() {
        return computer;
    }
}

//组装电脑（隐藏细节，用户只管调用(怕用户遗漏某个部分出错)）
class Director{

    public Computer build(ComputerBuilder computerBuilder){
        computerBuilder.setCpu();
        computerBuilder.setGpu();
        computerBuilder.setMemory();
        computerBuilder.setHd();

        return computerBuilder.build();
    }
}

public class AppTest {
    public static void main(String[] args) {
        Director director = new Director();

        Computer computer = director.build(new AdvancedComputerBuilder());

        System.out.println(computer);
    }
}