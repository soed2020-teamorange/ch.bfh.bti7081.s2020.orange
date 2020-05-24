package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.ActivityEntry;
import ch.bfh.bti7081.s2020.orange.backend.repositories.ActivityEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityEntryService {

  private final ActivityEntryRepository activityEntryRepository;


  public ActivityEntry saveNewActivityEntry(ActivityEntry ae) {
    return this.activityEntryRepository.save(ae);
  }

}
