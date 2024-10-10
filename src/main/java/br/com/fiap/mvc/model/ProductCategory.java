package br.com.fiap.mvc.model;

public enum ProductCategory {
    SMARTPHONES("Smartphones"),
    LAPTOPS_AND_NOTEBOOKS("Notebooks"),
    TELEVISIONS("Televisores"),
    TABLETS("Tablets"),
    AUDIO_EQUIPMENT("Aparelhos de Áudio"),
    CAMERAS_AND_CAMCORDERS("Câmeras e Filmadoras"),
    ACCESSORIES_AND_PERIPHERALS("Acessórios e Periféricos");


    private String label;

    ProductCategory(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
