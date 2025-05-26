package com.example.memo.repository;

import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {
    MemoResponseDto saveMemo(Memo memo);
    List<MemoResponseDto> findAllMemos();
    Optional<Memo> findMemoById(Long id);
    void deleteMemo(Long id);
    int updateMemo(Long id, String title, String contents);
}
