package main.java.com.check.service;

import com.check.model.dto.CheckDto;
import com.check.model.dto.CheckDtoList;
import main.java.com.check.domain.Check;
import main.java.com.check.repository.CheckDao;
import main.java.com.check.repository.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugene on 14.04.2014.
 */
@Service
@Transactional(readOnly = true)
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckDao checkDao;

    @Override
    public CheckDtoList getChecks() {
        List<Check> checkList = checkDao.getAllChecks();
        CheckDtoList checkDtoList = new CheckDtoList();
        List<CheckDto> checkDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(checkList)) {
            for (Check check : checkList) {
                checkDtos.add(new CheckDto(check.getId(), check.getName(), check.getDescription(), check.getStartDate(), check.getDuration(), check.getPhoto()));
            }
        }
        checkDtoList.setChecks(checkDtos);
        return checkDtoList;
    }

    @Override
    public CheckDto getCurrentCheck() {
        Check check = checkDao.getNextCheck();
        return new CheckDto(check.getId(), check.getName(), check.getDescription(), check.getStartDate(), check.getDuration(), check.getPhoto());
    }

}
