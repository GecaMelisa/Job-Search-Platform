package ba.edu.ibu.job.search.platform.rest.dto;

import java.util.List;

public class JobPageDTO {
    private Long total;
    private List<JobDTO> data;

    public JobPageDTO(Long total, List<JobDTO> data) {
        this.total = total;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<JobDTO> getData() {
        return data;
    }

    public void setData(List<JobDTO> data) {
        this.data = data;
    }
}
