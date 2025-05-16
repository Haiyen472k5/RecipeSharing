-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3307
-- Thời gian đã tạo: Th5 16, 2025 lúc 03:28 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `recipe_sharing`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `usage_count` bigint(20) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `name`, `usage_count`) VALUES
(1, 'Bún', 0),
(2, 'Cơm', 0),
(3, 'Mì', 0),
(4, 'Món tráng miệng', 0),
(5, 'Đồ uống', 0),
(6, 'Món ăn sáng', 0),
(7, 'Món ăn chay', 0),
(21, 'Khác', 0),
(22, 'Bánh mì', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comments`
--

CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL,
  `content` text NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `recipe_id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `comments`
--

INSERT INTO `comments` (`id`, `content`, `user_id`, `recipe_id`, `created_at`) VALUES
(1, 'Món này ngon thật!', 2, 1, '2025-05-12 16:57:51'),
(2, 'Làm thử rồi, cực dễ ăn', 3, 1, '2025-05-12 16:57:51'),
(3, 'Phở đậm đà vừa miệng!', 3, 2, '2025-05-12 16:57:51'),
(4, 'Nhìn ảnh thôi đã muốn ăn', 2, 2, '2025-05-12 16:57:51'),
(21, 'Ngon tuyệt vời!', 2, 4, '2025-05-15 08:05:53'),
(22, 'Món này mình rất thích.', 1, 2, '2025-05-15 08:05:53'),
(23, 'Dễ làm và hấp dẫn.', 4, 7, '2025-05-15 08:05:53'),
(24, 'Chuẩn vị Hàn Quốc luôn.', 1, 5, '2025-05-15 08:05:53');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `follows`
--

CREATE TABLE `follows` (
  `id` bigint(20) NOT NULL,
  `follower_id` bigint(20) DEFAULT NULL,
  `following_id` bigint(20) DEFAULT NULL,
  `followed_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `follows`
--

INSERT INTO `follows` (`id`, `follower_id`, `following_id`, `followed_at`) VALUES
(1, 1, 2, NULL),
(2, 1, 3, NULL),
(3, 2, 1, NULL),
(4, 3, 1, NULL),
(5, 4, 1, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `likes`
--

CREATE TABLE `likes` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `recipe_id` bigint(20) DEFAULT NULL,
  `liked_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `likes`
--

INSERT INTO `likes` (`id`, `user_id`, `recipe_id`, `liked_at`) VALUES
(2, 2, 1, NULL),
(4, 3, 1, NULL),
(5, 2, 2, NULL),
(6, 1, 2, '2025-05-12 17:43:23'),
(8, 5, 2, '2025-05-12 17:43:23'),
(10, 3, 2, '2025-05-12 17:43:23'),
(12, 4, 1, '2025-05-12 17:43:23'),
(13, 4, 2, '2025-05-12 17:43:23'),
(14, 1, 5, '2025-05-15 08:06:51'),
(15, 2, 4, '2025-05-15 08:06:51'),
(17, 5, 7, '2025-05-15 08:06:51');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ratings`
--

CREATE TABLE `ratings` (
  `id` bigint(20) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `recipe_id` bigint(20) DEFAULT NULL,
  `rated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ratings`
--

INSERT INTO `ratings` (`id`, `score`, `user_id`, `recipe_id`, `rated_at`) VALUES
(1, 5, 2, 1, NULL),
(2, 4, 4, 1, NULL),
(4, 5, 1, 10, '2025-05-15 08:09:07'),
(5, 4, 3, 5, '2025-05-15 08:09:07'),
(6, 4, 2, 7, '2025-05-15 08:09:07'),
(7, 1, 5, 4, '2025-05-15 08:09:07');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `recipes`
--

CREATE TABLE `recipes` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `image_url` text DEFAULT NULL,
  `description` text DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `recipes`
--

INSERT INTO `recipes` (`id`, `title`, `image_url`, `description`, `user_id`, `category_id`) VALUES
(1, 'Bánh mì kẹp trứng', '/images/banhmi.jpg', 'Món ăn sáng nhanh gọn', 1, 6),
(2, 'Phở bò Hà Nội', '/images/pho.jpg', 'Truyền thống miền Bắc', 1, 1),
(4, 'Bún trộn', '/images/buntron.jpg', 'Bún trộn nhiều topping với nước mắm chua ngọt.', 4, 1),
(5, 'Cơm kimchi', '/images/comkimchi.jpg', 'Cơm chiên với kimchi và trứng lòng đào.', 5, 2),
(6, 'Tokbokki', '/images/dookki.jpg', 'Bánh gạo cay Hàn Quốc vị truyền thống.', 3, 21),
(7, 'Bánh flan', '/images/flan.jpg', 'Món tráng miệng mềm mịn với caramel.', 1, 4),
(8, 'Kimchi', '/images/kimchi.jpg', 'Kimchi cay nồng, chuẩn vị Hàn Quốc.', 2, 21),
(10, 'Nem nướng', '/images/nemnuong.jpg', 'Nem nướng ăn kèm rau sống và bún.', 2, 21);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `saves`
--

CREATE TABLE `saves` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `recipe_id` bigint(20) DEFAULT NULL,
  `saved_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `saves`
--

INSERT INTO `saves` (`id`, `user_id`, `recipe_id`, `saved_at`) VALUES
(2, 1, 1, '2025-05-12 17:41:53'),
(3, 1, 2, '2025-05-12 17:41:53'),
(4, 2, 2, '2025-05-12 17:41:53'),
(5, 5, 1, '2025-05-12 17:41:53');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `avatar_url` text DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `is_admin` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `username`, `full_name`, `avatar_url`, `date_of_birth`, `is_admin`) VALUES
(1, 'lin123', 'nin no', '/images/banhmi.jpg', '2003-05-12', 0),
(2, 'haiyen47', 'Hai Yen', '/images/flan.jpg', '2002-11-23', 0),
(3, 'maianh', 'Chung Thị Mai Anh', '/images/banhmi.jpg', '2003-03-02', 0),
(4, 'phuongnt', 'Nguyễn Thị Phương', '/images/comkimchi.jpg', '2001-07-21', 0),
(5, 'lehuyanh', 'Lê Huy Anh', '/images/kimchi.jpg', '2003-08-09', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD UNIQUE KEY `name_2` (`name`);

--
-- Chỉ mục cho bảng `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Chỉ mục cho bảng `follows`
--
ALTER TABLE `follows`
  ADD PRIMARY KEY (`id`),
  ADD KEY `follower_id` (`follower_id`),
  ADD KEY `following_id` (`following_id`);

--
-- Chỉ mục cho bảng `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Chỉ mục cho bảng `ratings`
--
ALTER TABLE `ratings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Chỉ mục cho bảng `recipes`
--
ALTER TABLE `recipes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `fk_recipe_category` (`category_id`);

--
-- Chỉ mục cho bảng `saves`
--
ALTER TABLE `saves`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT cho bảng `comments`
--
ALTER TABLE `comments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT cho bảng `follows`
--
ALTER TABLE `follows`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `likes`
--
ALTER TABLE `likes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT cho bảng `ratings`
--
ALTER TABLE `ratings`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `recipes`
--
ALTER TABLE `recipes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `saves`
--
ALTER TABLE `saves`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `follows`
--
ALTER TABLE `follows`
  ADD CONSTRAINT `follows_ibfk_1` FOREIGN KEY (`follower_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `follows_ibfk_2` FOREIGN KEY (`following_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `likes`
--
ALTER TABLE `likes`
  ADD CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`);

--
-- Các ràng buộc cho bảng `ratings`
--
ALTER TABLE `ratings`
  ADD CONSTRAINT `ratings_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `ratings_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`);

--
-- Các ràng buộc cho bảng `recipes`
--
ALTER TABLE `recipes`
  ADD CONSTRAINT `fk_recipe_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `recipes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `saves`
--
ALTER TABLE `saves`
  ADD CONSTRAINT `saves_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `saves_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
