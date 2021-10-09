package com.epam.estart.service.impl;

import com.epam.estart.dto.MemberOnBoard;
import com.epam.estart.entity.MemberOnBoardEntity;
import com.epam.estart.entity.ProjectEntity;
import com.epam.estart.repository.MemberOnBoardRepository;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class MemberOnBoardService
    extends AbstractService<Long, MemberOnBoard, MemberOnBoardEntity, MemberOnBoardRepository> {
  MemberOnBoardService(MemberOnBoardRepository repository) {
    super(repository);
  }

  @Override
  public Class<MemberOnBoardEntity> getEntityClass() {
    return MemberOnBoardEntity.class;
  }

  @Override
  public Class<MemberOnBoard> getDTOClass() {
    return MemberOnBoard.class;
  }

  public void createAllByProjectEntity(ProjectEntity projectEntity) {
    Set<MemberOnBoardEntity> membersOnBoard = projectEntity.getMembersOnBoard();
    membersOnBoard.forEach(memberOnBoardEntity -> memberOnBoardEntity.setProjectId(projectEntity.getId()));
    repository.saveAll(membersOnBoard);
  }

  public void removeAll(Set<MemberOnBoardEntity> membersOnBoard) {
    repository.deleteAll(membersOnBoard);
  }
}
