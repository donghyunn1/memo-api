package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/memos") // prefix
public class MemoController {

    // 자료구조가 DB 역할 수행
    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto requestDto) {

        // MemoId 식별자 계산
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;

        // 요청받은 데이터로 Memo 객체 생성
        Memo memo = new Memo(memoId, requestDto.getTitle(), requestDto.getContents());

        // Inmemory DB에 Memo 저장
        memoList.put(memoId, memo);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.CREATED);
    }


    @GetMapping
    public List<MemoResponseDto> findAllMemos() {

        // init List
        List<MemoResponseDto> responseList = new ArrayList<>();

        // HashMap<Memo> -> List<MemoResponseDto>
        for (Memo memo : memoList.values()) {
            MemoResponseDto responseDto = new MemoResponseDto(memo);
            responseList.add(responseDto);
        }

        // Map To List
        // responseList = memoList.values().stream().map(MemoResponseDto::new).toList();
        return responseList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id) {

        // 식별자의 Memo가 없다면?
        Memo memo = memoList.get(id);

        // NPE 방지
        if (memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public MemoResponseDto updateMemoById(
            @PathVariable Long id,
            @RequestBody MemoRequestDto requestDto
    ) {

        Memo memo = memoList.get(id);

        memo.update(requestDto);

        return new MemoResponseDto(memo);
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(
            @PathVariable Long id
    ) {
        memoList.remove(id);
    }
}
