package com.epam.estart.service.impl;

import com.epam.estart.dto.MemberOnBoard;
import com.epam.estart.dto.Project;
import com.epam.estart.entity.MemberOnBoardEntity;
import com.epam.estart.entity.ProjectEntity;
import com.epam.estart.repository.MemberOnBoardRepository;
import java.util.HashSet;
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

  public Set<MemberOnBoardEntity> createAllByProjectEntity(Project project) {
    Set<MemberOnBoardEntity> membersOnBoard = modelMapper.map(project, ProjectEntity.class).getMembersOnBoard();
    // TODO: rework this, it's only quick fix
    membersOnBoard = membersOnBoard == null ? new HashSet<>() : membersOnBoard;
    membersOnBoard.forEach(memberOnBoardEntity -> memberOnBoardEntity.setProjectId(project.getId()));
    repository.saveAll(membersOnBoard);
    return membersOnBoard;
  }

  public void removeAll(Set<MemberOnBoardEntity> membersOnBoard) {
    repository.deleteAll(membersOnBoard);
  }
}
