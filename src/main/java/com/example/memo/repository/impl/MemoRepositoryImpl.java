package com.example.memo.repository.impl;

import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoRepositoryImpl implements MemoRepository {
    private final Map<Long, Memo> memoList = new HashMap<>();

    @Override
    public Memo saveMemo(Memo memo) {

        // memo 식별자 자동 생성
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;
        memo.setId(memoId);

        memoList.put(memoId, memo);

        return memo;
    }

}
