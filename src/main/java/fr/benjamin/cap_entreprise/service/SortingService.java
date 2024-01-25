package fr.benjamin.cap_entreprise.service;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface SortingService<T> {

    List<T> findAllSorted(Sort sort);

}
