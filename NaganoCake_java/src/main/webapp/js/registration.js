/**
 * 新規会員登録フォームのJavaScript
 * クライアントサイドバリデーションとUI機能を提供
 */

// 下のエラーはコードチェックによるエラーのため問題ありません。
//（実際のコードは間違っておらず、jsはブラウザ上で動くためeclipseで起きているこのエラーについては気にしなくてよい。
// 郵便番号の自動フォーマット機能
document.getElementById('postalCode')?.addEventListener('input', function(e) {
    // 数字以外を除去
    let value = e.target.value.replace(/\D/g, '');

    e.target.value = value;
});

// フォーム送信前のバリデーション
document.querySelector('form').addEventListener('submit', function(e) {
    // パスワードの長さチェック
    const password = document.getElementById('password').value;
    if (password.length < 8) {
        e.preventDefault(); // フォーム送信を中止
        alert('パスワードは8文字以上で入力してください');
        return false;
    }
    
    // メールアドレスの形式チェック
    const email = document.getElementById('email').value;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // 基本的なメール形式正規表現
    if (!emailRegex.test(email)) {
        e.preventDefault();
        alert('有効なメールアドレスを入力してください');
        return false;
    }
    
    // 必須項目の空白チェック
    const lastName = document.getElementById('lastName').value.trim();
    const firstName = document.getElementById('firstName').value.trim();
    if (!lastName || !firstName) {
        e.preventDefault();
        alert('姓と名は必須項目です');
        return false;
    }
    
    // すべてのバリデーション通過
    return true;
});

// 入力フィールドのリアルタイムバリデーション（任意機能）
document.querySelectorAll('input[required]').forEach(input => {
    input.addEventListener('blur', function() {
        // 必須項目が空の場合に視覚的フィードバック
        if (!this.value.trim()) {
            this.style.borderColor = '#ff6b6b'; // エラー色に変更
        } else {
            this.style.borderColor = 'rgba(255, 255, 255, 0.3)'; // 元の色に戻す
        }
    });
});