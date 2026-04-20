
◆ 環境
java : 21.0.9 LTS
Eclipse : 2024-09
tomcat : 10.1.29 (Eclipseに内包済)
MySQL : 8.0.35.0
MySQLconnector : 9.4.0

◆ Ruby版のNaganoCake
https://github.com/team-GameBoys/GameBoys-github

アーキテクチャや画面レイアウト等をRuby版NaganoCakeから受け継いでいます。


簡単にNaganoCakeJavaのアーキテクチャです。
1. Presentation Layer（表示層）
＝ JSP ＋ Controller（Servlet）
役割：
ユーザーからの入力を受け取る
画面を表示する
DAO や Service に処理を依頼する

NaganoCakeJava の例：
CartController
ItemListController
cart.jsp
item_list.jsp


2. Business Logic Layer（業務ロジック層）
＝ Service（今回は薄め or 省略気味）
役割：
業務ルールを実行
Controller と DAO の間をつなぐ
トランザクション管理（本来はここ）
NaganoCake の規模だと Service は薄いけど、

もし作るなら：
CartService
ItemService


3. Data Access Layer（データアクセス層）
＝ DAO ＋ Entity
役割：
DB とやり取りする
SQL を実行する
Entity に詰めて返す

NaganoCake の例：
CartItemDAO
ItemDAO
CartItemEntity
ItemEntity
