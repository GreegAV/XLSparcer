public class Data {
    private String fio;
    private String date;
    private String summ;
    private String summOther;

    public Data() {
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSumm() {
        return summ;
    }

    public void setSumm(String summ) {
        this.summ = summ;
    }

    public String getSummOther() {
        return summOther;
    }

    public void setSummOther(String summOther) {
        this.summOther = summOther;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        if (fio != null ? !fio.equals(data.fio) : data.fio != null) return false;
        if (date != null ? !date.equals(data.date) : data.date != null) return false;
        if (summ != null ? !summ.equals(data.summ) : data.summ != null) return false;
        return summOther != null ? summOther.equals(data.summOther) : data.summOther == null;
    }

    @Override
    public int hashCode() {
        int result = fio != null ? fio.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (summ != null ? summ.hashCode() : 0);
        result = 31 * result + (summOther != null ? summOther.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Data{" +
                "fio='" + fio + '\t' +
                ", date='" + date + '\t' +
                ", summ='" + summ + '\t' +
                ", summOther='" + summOther + '\t' +
                '}';
    }
}
