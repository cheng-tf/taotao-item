package com.taotao.springboot.item.domain.result;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: EasyUIDataGridResult</p>
 * <p>Description: 前端结果类</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-05 16:41</p>
 * @author ChengTengfei
 * @version 1.0
 */
public class EasyUIDataGridResult implements Serializable {

    private static final long serialVersionUID = -5847440530290013318L;

    // 总记录数
    private long total;
    // 记录列表
    private List rows;

    public EasyUIDataGridResult() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}

