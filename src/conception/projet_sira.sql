-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 24 déc. 2021 à 11:04
-- Version du serveur :  10.3.14-MariaDB
-- Version de PHP :  7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projet_sira`
--

-- --------------------------------------------------------

--
-- Structure de la table `agence`
--

DROP TABLE IF EXISTS `agence`;
CREATE TABLE IF NOT EXISTS `agence` (
  `id_agence` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(50) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL,
  `cp` int(11) DEFAULT NULL,
  `ville` varchar(50) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `photo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_agence`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `agence`
--

INSERT INTO `agence` (`id_agence`, `titre`, `adresse`, `cp`, `ville`, `description`, `photo`) VALUES
(1, 'Agence de Nantes', '15 rue de JC', 13000, 'Nantes', 'notre agence Nantes...', 'agence_Nantes.jpg'),
(2, 'agence Paris', '10 rue de paris', 75002, 'Paris', 'Agence de 2eme....', 'ag1.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id_commande` int(11) NOT NULL AUTO_INCREMENT,
  `membre` int(11) NOT NULL,
  `vehicule` int(11) NOT NULL,
  `debut` datetime NOT NULL,
  `fin` datetime NOT NULL,
  `prix_total` decimal(15,2) DEFAULT NULL,
  `date_enregistrement` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id_commande`),
  KEY `vehicule` (`vehicule`),
  KEY `membre` (`membre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `membre`
--

DROP TABLE IF EXISTS `membre`;
CREATE TABLE IF NOT EXISTS `membre` (
  `id_membre` int(11) NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(20) NOT NULL,
  `mdp` varchar(50) DEFAULT NULL,
  `civility` enum('Femme','Homme') DEFAULT NULL,
  `prenom` varchar(50) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `statut` enum('client','admin') DEFAULT 'client',
  `date_enregistrement` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id_membre`),
  UNIQUE KEY `pseudo` (`pseudo`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `membre`
--

INSERT INTO `membre` (`id_membre`, `pseudo`, `mdp`, `civility`, `prenom`, `nom`, `email`, `statut`, `date_enregistrement`) VALUES
(1, 'toto', 'ilci', 'Homme', 'Harouna kane', 'Kane', 'hk@gmail.com', 'admin', '2021-12-22 11:15:08'),
(2, 'cdacda', 'ilci', 'Femme', 'Julie', 'tata', 'hk@gmail.com', 'client', '2021-12-22 11:17:28'),
(3, 'titi', 'ilci', 'Homme', 'kaba ly', 'tata', 'hk@gmail.com', 'client', '2021-12-22 12:58:36');

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE IF NOT EXISTS `vehicule` (
  `id_vehicule` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(50) NOT NULL,
  `marque` varchar(50) DEFAULT NULL,
  `modele` varchar(50) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `photo` varchar(50) DEFAULT NULL,
  `prix_journalier` decimal(15,2) DEFAULT NULL,
  `agence` int(11) NOT NULL,
  PRIMARY KEY (`id_vehicule`),
  KEY `id_agence` (`agence`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`vehicule`) REFERENCES `vehicule` (`id_vehicule`),
  ADD CONSTRAINT `commande_ibfk_2` FOREIGN KEY (`membre`) REFERENCES `membre` (`id_membre`);

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `vehicule_ibfk_1` FOREIGN KEY (`agence`) REFERENCES `agence` (`id_agence`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
