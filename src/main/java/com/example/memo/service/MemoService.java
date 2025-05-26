package com.example.memo.service;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;

public interface MemoService {
    MemoResponseDto saveMemo(MemoRequestDto requestDto);
}
