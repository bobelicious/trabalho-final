package br.com.serratec.trabalhofinal.exceptions;


public class ErroResposta {
    private Integer status;
    private String titulo;
    private String detail;
    private String developerMessage;
    private Long timeStamp;

    public ErroResposta(Integer status, String titulo, String detail, String developerMessage, Long timeStamp) {
        this.status = status;
        this.titulo = titulo;
        this.detail = detail;
        this.developerMessage = developerMessage;
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getDeveloperMessage() {
        return developerMessage;
    }
    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
    public Long getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

}
