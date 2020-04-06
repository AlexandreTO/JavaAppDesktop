-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 06 avr. 2020 à 17:06
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projet_two`
--

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Prenom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `PWD` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DateDeNaissance` date NOT NULL,
  `Adresse` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Telephone` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Ville` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CodePostal` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`id`, `Nom`, `Prenom`, `Email`, `PWD`, `DateDeNaissance`, `Adresse`, `Telephone`, `Username`, `Ville`, `CodePostal`) VALUES
(1, 'TO', 'ALex', 'alexandreto1996@gmail.com', '$2y$10$AFjse5YTXOsaF.r7CU0bYeL4SQeQKXtZ/gJjo.828TYtXzotOScXi', '1996-11-18', '52 Avenue du Général De Gaulle', '0750070877', 'Net', 'Bussy Saint Georges', '77600'),
(2, 'Herrington', 'Billy', 'billy@gmail.com', '$2y$10$iv5D1wPOcCzlHGYJQKSmc.qOKmHIqkZojVfhfGXtfWKVR4uj7.Q26', '1980-11-02', '69 Ram Ranch', '0652157498', 'Aniki', 'Paris', '75015');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id_produit` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id_produit`,`id`),
  KEY `Commande_clients0_FK` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id_produit` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Reference` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Prix` float NOT NULL,
  `Description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Categorie` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_produit`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id_produit`, `Nom`, `Reference`, `Prix`, `Description`, `Image`, `Categorie`) VALUES
(1, 'Macaron chocolat', 'MC01ES', 0.25, 'Superbe macaron au chocolat', 'macaron_choco.jpg', 'macaron'),
(2, 'Macaron vanille', 'MV02ES', 0.25, 'Délicieux macaron à la vanille', 'macaron_van.jpg', 'macaron'),
(3, 'Macaron fraise', 'MF03ES', 0.25, 'Un délice de macaron saveur fraise', 'macaron_fr.jpg', 'macaron'),
(4, 'Cookies chocolat au lait', 'CCAL04ES', 0.35, 'Grand cookie au pépite de chocolat au lait', 'cookie_choco.jpg', 'cookies'),
(5, 'Muffin au caramel', 'MAC05ES', 0.35, 'Excellent muffin fourré au caramel.', 'muffin_car.jpg', 'muffin'),
(6, 'Tartes aux fraises', 'TAF06ES', 1, 'Merveilleuse tartes aux fraises', 'tarte_fraise.jpg', 'tarte'),
(7, 'Gâteau au chocolat', 'GACH07ES', 2, 'Le classique gâteau au chocolat revisité par nos plus grands pâtissiers ', 'gateau_chocolat.jpg', 'gâteau'),
(8, 'Tarte aux pommes', 'TAPM08ES', 1.5, 'Délicieuse tartes aux pommes de nos régions', 'tarte_aux_pommes.jpg', 'tarte'),
(9, 'Macaron pistache', 'MPCHO9ES', 0.15, 'Savoureux macaron à la pistache', 'macaron_pistache.jpg', 'macaron'),
(10, 'Macaron citron', 'MCTRN10ES', 0.15, 'Pour les fans d\'acidité ', 'macaron_citron.jpg', 'macaron'),
(11, 'Macaron cassis', 'MCSS11ES', 0.25, 'Pour les amoureux du cassis', 'macaron_cassis.jpg', 'macaron'),
(12, 'Gâteau à la rose', 'GALR12ES', 18.55, 'Fabuleux gâteau Hermès à la rose', 'gateau_rose.jpg', 'gateau');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `Commande_Produit_FK` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id_produit`),
  ADD CONSTRAINT `Commande_clients0_FK` FOREIGN KEY (`id`) REFERENCES `clients` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
