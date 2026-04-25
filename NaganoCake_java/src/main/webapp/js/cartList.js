/**
 * 
 */// 数量変更時に軽くアニメーション
document.querySelectorAll(".amount-input").forEach(input => {
    input.addEventListener("change", () => {
        input.classList.add("changed");
        setTimeout(() => input.classList.remove("changed"), 300);
    });
});

// 削除ボタンに確認ダイアログ
document.querySelectorAll(".btn-delete").forEach(btn => {
    btn.addEventListener("click", (e) => {
        if (!confirm("本当に削除しますか？")) {
            e.preventDefault();
        }
    });
});

// カート全削除にも確認
const deleteAllBtn = document.querySelector(".btn-danger");
if (deleteAllBtn) {
    deleteAllBtn.addEventListener("click", (e) => {
        if (!confirm("カートを空にします。よろしいですか？")) {
            e.preventDefault();
        }
    });
}