-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : sam. 23 jan. 2021 à 12:33
-- Version du serveur :  8.0.18
-- Version de PHP : 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `covoiturage`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateurs`
--

CREATE TABLE `administrateurs` (
  `Id_Admin` int(10) UNSIGNED NOT NULL,
  `Nom` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Mot_de_passe` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Tel` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Adresse` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `administrateurs`
--

INSERT INTO `administrateurs` (`Id_Admin`, `Nom`, `Email`, `Mot_de_passe`, `Tel`, `Adresse`) VALUES
(1, 'admin', 'admin@gmail.com', 'dgplq', '0600000000', 'ENSA MARRAKECH');

-- --------------------------------------------------------

--
-- Structure de la table `envoyer_messages`
--

CREATE TABLE `envoyer_messages` (
  `id_Utilisateurs_src` int(10) UNSIGNED NOT NULL,
  `id_Utilisateurs_dst` int(10) UNSIGNED NOT NULL,
  `contenue` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `favoris`
--

CREATE TABLE `favoris` (
  `Id_Utilisateur` int(10) UNSIGNED NOT NULL,
  `Id_offre` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `favoris`
--

INSERT INTO `favoris` (`Id_Utilisateur`, `Id_offre`) VALUES
(34, 20);

-- --------------------------------------------------------

--
-- Structure de la table `offres`
--

CREATE TABLE `offres` (
  `Id_offer` int(10) UNSIGNED NOT NULL,
  `Titre` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `prix` float NOT NULL,
  `Date_depart` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Heure_depart` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Ville_depart` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Ville_arrive` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Nbr_places` int(10) UNSIGNED NOT NULL,
  `Bagage` varchar(3) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Id_Utilisateur` int(10) UNSIGNED NOT NULL,
  `Id_Admin` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `offres`
--

INSERT INTO `offres` (`Id_offer`, `Titre`, `prix`, `Date_depart`, `Heure_depart`, `Ville_depart`, `Ville_arrive`, `Nbr_places`, `Bagage`, `Id_Utilisateur`, `Id_Admin`) VALUES
(19, 'OFFRE Marrakech', 200, '2021-01-18', '01:00', 'TAN TAN', 'AL HOCEIMA', 2, 'NON', 33, 1),
(20, 'OFFRE2', 100, '2021-01-18', '10:00', 'AL HOCEIMA', 'AGADIR', 80, 'OUI', 33, 1);

-- --------------------------------------------------------

--
-- Structure de la table `reservations`
--

CREATE TABLE `reservations` (
  `Id_Utilisateur` int(10) UNSIGNED NOT NULL,
  `Id_offer` int(10) UNSIGNED NOT NULL,
  `message` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Nbr_places` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `reservations`
--

INSERT INTO `reservations` (`Id_Utilisateur`, `Id_offer`, `message`, `Nbr_places`) VALUES
(34, 19, '', 2),
(34, 19, '', 2),
(34, 19, '', 5),
(34, 19, '', 1);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `Id_Utilisateur` int(10) UNSIGNED NOT NULL,
  `Nom` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Mot_de_passe` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Tel` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Adresse` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Id_Admin` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`Id_Utilisateur`, `Nom`, `Email`, `Mot_de_passe`, `Tel`, `Adresse`, `Id_Admin`) VALUES
(31, 'HAITHAM OUMERZOUG', 'haithamoumerzoug31@gmail.com', 'kdlwkdp', '0642509795', 'AGADIR', 1),
(33, 'EL MOUSSAOUI SAID', 'elmoussaouis074@gmail.com', 'vdlg', '0624408753', 'AIT OURIR , EL HAOUZ ', 1),
(34, 'AMINE BOUGUESSA', 'amiox.123@gmail.com', 'dplqh', '0708046877', 'MARRAKECH', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `administrateurs`
--
ALTER TABLE `administrateurs`
  ADD PRIMARY KEY (`Id_Admin`),
  ADD UNIQUE KEY `c002` (`Email`);

--
-- Index pour la table `envoyer_messages`
--
ALTER TABLE `envoyer_messages`
  ADD KEY `fk_Evoyer_messages_Utilisateurs1` (`id_Utilisateurs_src`),
  ADD KEY `fk_Evoyer_messages_Utilisateurs2` (`id_Utilisateurs_dst`);

--
-- Index pour la table `favoris`
--
ALTER TABLE `favoris`
  ADD PRIMARY KEY (`Id_Utilisateur`,`Id_offre`),
  ADD KEY `fk_Favoris_Utilisateurs1` (`Id_Utilisateur`),
  ADD KEY `fk_Favoris_Offre1` (`Id_offre`);

--
-- Index pour la table `offres`
--
ALTER TABLE `offres`
  ADD PRIMARY KEY (`Id_offer`),
  ADD KEY `fk_Offre_Utilisateurs1` (`Id_Utilisateur`),
  ADD KEY `fk_Offres_Administrateurs1` (`Id_Admin`);

--
-- Index pour la table `reservations`
--
ALTER TABLE `reservations`
  ADD KEY `fk_Reservations_Utilisateurs1` (`Id_Utilisateur`),
  ADD KEY `fk_Reservations_Offre1` (`Id_offer`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`Id_Utilisateur`),
  ADD UNIQUE KEY `c001` (`Email`),
  ADD KEY `fk_Utilisateurs_Administrateurs1` (`Id_Admin`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `administrateurs`
--
ALTER TABLE `administrateurs`
  MODIFY `Id_Admin` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `offres`
--
ALTER TABLE `offres`
  MODIFY `Id_offer` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `Id_Utilisateur` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
