# Springwebdb

Spring Bootの基本的な処理の流れを学ぶため、ITスクールの授業で講師の説明と教材をもとに作成した会員管理Webアプリケーションです。

授業で基本的なCRUD機能を実装した後、コードと動作を見直し、パスワードの暗号化や機密情報の分離などの改善を加えました。

## 開発期間

2026年9月～現在

## 主な機能

- 会員登録
- 会員一覧の表示
- 会員情報の更新
- IDを利用した会員情報の削除
- 名前・パスワード・メールアドレスの入力値検証
- BCryptによるパスワードの暗号化

## 使用技術

- Java 17
- Spring Boot
- Spring Security
- MyBatis
- MySQL
- Thymeleaf
- HTML
- Maven
- Git / GitHub

## プロジェクト構成

- Controller：画面からのリクエスト受付とレスポンスの制御
- Service：入力値の検証や会員情報に関する処理
- Mapper：MyBatisを利用したデータベース操作
- DTO：画面と各層の間で利用する会員データの保持
- View：Thymeleafを利用した画面表示

## 改善した点

基本的なCRUD機能の実装後、以下の改善を行いました。

- パスワードを平文ではなく、BCryptで暗号化して保存
- データベースの接続パスワードを別の設定ファイルに分離
- 氏名とメールアドレスによる削除から、IDによる削除へ変更
- 名前・パスワード・メールアドレスの入力値検証を追加

## データベースの準備

MySQLに以下のデータベースとテーブルを作成します。

```sql
CREATE DATABASE springbootperson;

USE springbootperson;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);
```

## 機密情報の設定

`src/main/resources/application-secret.properties`を作成し、MySQLのパスワードを設定します。

```properties
spring.datasource.password=YOUR_PASSWORD
```

機密情報を含むため、`application-secret.properties`は公開せず、`.gitignore`に追加します。

## 今後の改善予定

- ログイン機能と認証・認可機能の追加
- エラー画面の追加
- テストコードの作成
- Webサービスとしてのデプロイ
