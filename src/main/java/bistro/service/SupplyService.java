package bistro.service;



import java.util.List;


import org.hibernate.Session;

import bistro.bean.CampaignBean;
import bistro.bean.SupplyBean;
import bistro.dao.CampaignDao;
import bistro.dao.SupplyDao;


public class SupplyService {
    
    private SupplyDao supplyDao;

    public SupplyService(Session session) {
        this.supplyDao = new SupplyDao(session);
    }

    // 查找指定ID的供應信息
    public SupplyBean findSupplyById(int id) {
        return supplyDao.findSupplyById(id);
    }

    // 查找所有供應信息
    public List<SupplyBean> findAllSupply() {
        return supplyDao.findAllSupply();
    }

    // 查找所有供應信息，包括供應商和員工
    public List<SupplyBean> findAllSupplyWithDetails() {
        return supplyDao.findAllSupply();
    }

    // 创建新的供應信息
    public boolean createSupply(SupplyBean supplyBean) {
        return supplyDao.createSupply(supplyBean);
    }

    // 更新供應信息
    public boolean updateSupply(SupplyBean supplyBean) {
        return supplyDao.updateSupply(supplyBean);
    }

    // 删除供應信息
    public boolean deleteSupply(SupplyBean supplyBean) {
        return supplyDao.deleteSupply(supplyBean);
    }
}

	