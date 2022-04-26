package web.charmi.payload.response;

public class FileInfo {
    private String FileName;
    private String Url;
    private Integer Sort;

    public FileInfo(String FileName, String Url, Integer Sort) {
        this.FileName=FileName;
        this.Url=Url;
        this.Sort=Sort;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName=fileName;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url=url;
    }

    public Integer getSort() {
        return Sort;
    }

    public void setSort(Integer sort) {
        Sort=sort;
    }
}
