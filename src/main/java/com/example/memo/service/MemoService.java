package com.example.memo.service;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;

import java.util.List;

public interface MemoService {
    MemoResponseDto saveMemo(MemoRequestDto requestDto);
    List<MemoResponseDto> findAllMemos();
    MemoResponseDto findMemoById(Long id);
    MemoResponseDto updateMemo(Long id, String title, String contents);
}
