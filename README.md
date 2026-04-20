🍰 NaganoCake Java – Architecture Overview
NaganoCake Java は、Ruby 版 NaganoCake（Rails）を参考にしつつ、
Java（Servlet / JSP）で再構築した EC サイトです。
アプリ全体は 3 層アーキテクチャ（Three-Tier Architecture） を採用しています。

🏛 1. Presentation Layer（表示層）
構成要素：JSP / Servlet（Controller）

この層はユーザーと直接やり取りする部分です。

役割：

ユーザーからの入力を受け取る

DAO や Service に処理を依頼する

JSP を使って画面を表示する

例：

CartController

ItemListController

cart.jsp

item_list.jsp

⚙️ 2. Business Logic Layer（業務ロジック層）
構成要素：Service（任意）

NaganoCake の規模では薄めですが、
本来は Controller と DAO の間で業務処理を担当する層です。

役割：

業務ルールの実行

Controller と DAO の橋渡し

トランザクション管理（本来はここ）

例（導入する場合）：

CartService

ItemService

🗄 3. Data Access Layer（データアクセス層）
構成要素：DAO / Entity

DB と直接やり取りする層です。

役割：

SQL の実行

DB のデータを Entity に詰めて返す

Controller や Service からの要求に応じてデータ取得・更新を行う

例：

CartItemDAO

ItemDAO

CartItemEntity

ItemEntity

📦 DTO（Data Transfer Object）
DTO は層ではなく データの受け渡し専用クラスです。
JOIN 結果や画面表示用のデータをまとめるために使用します。

例：

CartItemDTO（商品情報＋カート数量をまとめた表示用モデル）

🧱 Architecture Diagram
コード
Presentation Layer
   ├── JSP
   └── Controller (Servlet)
          ↓
Business Logic Layer (Service)
          ↓
Data Access Layer
   ├── DAO
   └── Entity
          ↓
       Database
📝 環境
Java : 21.0.9 LTS

Eclipse : 2024-09

Tomcat : 10.1.29

MySQL : 8.0.35

MySQL Connector/J : 9.4.0

Ruby 版 NaganoCake（参考元）：
https://github.com/team-GameBoys/GameBoys-github
