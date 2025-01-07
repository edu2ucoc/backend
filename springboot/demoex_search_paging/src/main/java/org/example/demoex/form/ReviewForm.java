package org.example.demoex.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewForm {
    @NotEmpty(message ="본문은 반드시 입력해야 하는 필수 항목입니다.")
    // 임시로 크기 제약 부여
    @Size(min=2, max=256) // 2이하는 의미 없는 도배 수준이다(컨셉설정)
    private String content;
}
