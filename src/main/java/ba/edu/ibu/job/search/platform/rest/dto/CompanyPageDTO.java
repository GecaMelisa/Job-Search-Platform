package ba.edu.ibu.job.search.platform.rest.dto;

import java.util.List;

public class CompanyPageDTO {
    private Long total;
    private List<CompanyDTO> data;

    public CompanyPageDTO(Long total, List<CompanyDTO> data) {
        this.total = total;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<CompanyDTO> getData() {
        return data;
    }

    public void setData(List<CompanyDTO> data) {
        this.data = data;
    }
}
