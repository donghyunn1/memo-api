package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import com.example.memo.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/memos") // prefix
public class MemoController {

    // 주입된 의존성을 변경할 수 없어 객체의 상태를 안전하게 유지할 수 있다.
    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping // 요청
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto requestDto) {
        // ServiceLayer 호출 및 응답
        return new ResponseEntity<>(memoService.saveMemo(requestDto), HttpStatus.CREATED);
    }


    @GetMapping
    public List<MemoResponseDto> findAllMemos() {

        return memoService.findAllMemos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id) {

        return new ResponseEntity<>(memoService.findMemoById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateMemo(
            @PathVariable Long id,
            @RequestBody MemoRequestDto requestDto
    ) {

        return new ResponseEntity<>(memoService.updateMemo(id, requestDto.getTitle(), requestDto.getContents()), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateTitle(
            @PathVariable Long id,
            @RequestBody MemoRequestDto requestDto
    ) {

        return new ResponseEntity<>(memoService.updateTitle(id, requestDto.getTitle(), requestDto.getContents()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {

        // memoList의 Key값에 id를 포함하고 있다면
        if (memoList.containsKey(id)) {
            // key가 id인 value 삭제
            memoList.remove(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        // 포함하고 있지 않은 경우
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
