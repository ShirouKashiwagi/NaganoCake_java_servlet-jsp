NaganoCake Java – Architecture Overview

Ruby 版 NaganoCake（Rails）を参考にしつつ、
Java（Servlet / JSP）で再構築した EC サイトです。

本プロジェクトは「Three-Tier Architecture（3 層アーキテクチャ）」を採用しています。

============================================================

Presentation Layer（表示層）
============================================================

構成要素：

JSP

Servlet（Controller）

役割：

ユーザーからの入力を受け取る

DAO や Service に処理を依頼する

JSP を使って画面を描画する

例：

CartController

ItemListController

cart.jsp

item_list.jsp

============================================================

Business Logic Layer（業務ロジック層）
============================================================

構成要素：

Service（任意）

NaganoCake の規模では薄めだが、本来は Controller と DAO の間で業務処理を行う層。

役割：

業務ルールの実行

Controller と DAO の橋渡し

トランザクション管理（本来はここ）

例（導入する場合）：

CartService

ItemService

============================================================

Data Access Layer（データアクセス層）
============================================================

構成要素：

DAO

Entity

役割：

SQL の実行

DB のデータを Entity に詰めて返す

Controller や Service からの要求に応じてデータ取得・更新を行う

例：

CartItemDAO

ItemDAO

CartItemEntity

ItemEntity

============================================================

DTO（Data Transfer Object）
============================================================

DTO は層ではなく「データの受け渡し専用クラス」。

JOIN 結果や画面表示用のデータをまとめるために使用する。

例：

CartItemDTO（商品情報＋カート数量をまとめた表示用モデル）

============================================================

Architecture Diagram（テキスト版）
============================================================

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

============================================================

Development Environment
============================================================

Java : 21.0.9 LTS
Eclipse : 2024-09
Tomcat : 10.1.29
MySQL : 8.0.35
MySQL Connector/J : 9.4.0

参考元（Ruby 版 NaganoCake）：
https://github.com/team-GameBoys/GameBoys-github
