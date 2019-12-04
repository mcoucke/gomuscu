package com.app.gomuscu.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Exercice {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nom;
    private String description;
    private String type;
    private String image;


    public Exercice(String nom, String description, String type, String image) {
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static Exercice[] populateExercice() {
        return new Exercice[] {
                new Exercice("Crunch", "Les épaules ne décollent que de quelques centimètres du sol, et le bas du dos et les hanches restent fixes.", "Abdominaux", "crunch"),
                new Exercice("Sit up", "Tendre les jambes puis ramener les genoux vers la poitrine.", "Abdominaux", "situp"),
                new Exercice("Relevé de jambes", "Fléchir les jambes légèrement et les ramener vers le tronc en gardant le bas du dos collé au banc.", "Abdominaux", "releve_jambe"),
                new Exercice("Flexions latérales", "Descendre l'haltère près du corps en évitant de pencher le tronc vers l’avant.", "Abdominaux", "flexion_laterale"),
                new Exercice("Torsion du Bassin", "Soulever les jambes en gardant les genoux fléchis et amener les pieds à droite puis à gauche par torsion de bassin.", "Abdominaux", "torsion_bassin"),
                new Exercice("Gainage", "Se positionner face au sol en équilibre sur les coudes et les pointes de pieds.", "Abdominaux", "gainage"),

                new Exercice("Pompes", "Descendre le buste jusqu'à frôler le sol avec la tête et remonter en restant gainé.", "Pectoraux", "pompe"),
                new Exercice("Développé haltères", "Monter les haltères bras tendus au dessus de la tête puis revenir lentement à la position de départ.", "Pectoraux", "developpe_haltere"),
                new Exercice("Développé couché barre", "Descendre la charge lentement jusqu'à frôler les pectoraux et revenir à la position initiale.", "Pectoraux", "developpe_coucher_barre"),
                new Exercice("Développé couché haltères", "Poussez chaque haltère vers le haut jusqu'à ce que vos bras soient tendus et réunis au-dessus de votre tête et redescendez.", "Pectoraux", "developpe_coucher_haltere"),
                new Exercice("Dips", "Descendre jusqu'à ce que les bras soient parallèle au sol puis remonter.", "Pectoraux", "dips"),
                new Exercice("Pull over", "Descendre la charge au maximum derrière la tête puis revenir à la position de départ.", "Pectoraux", "pull_over"),

                new Exercice("Tractions", "Monter en amenant le menton au niveau de la barre et redescendre sans à-coups.", "Dos", "traction"),
                new Exercice("Tirage poitrine", "Inclinez légèrement le dos vers l'arrière, tirez la barre vers vous jusqu'à la poitrine, puis revenir lentement à la position de départ dos droit.", "Dos", "tirage"),
                new Exercice("Tirage nuque", "Tirez la barre vers le bas jusqu'à la nuque, puis revenir lentement à la position de départ dos droit.", "Dos", "tirage"),
                new Exercice("Rowing barre", "Remonter la barre au niveau du bas des abdominaux puis redescendre lentement la barre jusqu'à la position initiale.", "Dos", "rowing_barre"),
                new Exercice("Tirage poulie basse", "Amener la charge contre les abdominaux en sortant bien la poitrine puis revenir à la position initiale.", "Dos", "tirage_poulie_basse"),
                new Exercice("Bûcheron", "Remonter la charge en montant le coude le plus haut possible puis revenir à la position initiale.", "Dos", "bucheron"),

                new Exercice("Élévations latérales", "Monter les deux bras en même temps latéralement jusqu'à hauteur d'épaules.", "Epaules", "elevation_laterale"),
                new Exercice("Élévations frontales", "Monter les deux bras en même temps frontalement jusqu'à hauteur d'épaules.", "Epaules", "elevation_frontale"),
                new Exercice("Développé militaire haltères", "Monter les haltères bras tendus au dessus de la tête puis revenir lentement à la position de départ.", "Epaules", "developpe_haltere"),
                new Exercice("Oiseaux", "Allongé sur le ventre sur un banc, monter les deux bras en même temps jusqu'à hauteur d'épaules.", "Epaules", "oiseaux"),
                new Exercice("Élévations latérales au cable", "Effectuez une élévation du bras en amenant le coude à hauteur de l’épaule puis revenir lentement à la position de départ.", "Epaules", "elevation_cable"),
                new Exercice("Développé nuque", "Dos droit, monter la barre bras tendus au dessus de la tête puis redescendez la barre au niveau de la nuque.", "Epaules", "developpe_nuque"),

                new Exercice("Curl haltères", "Amener l'haltère au niveau de l'épaule à la force des biceps, en effectuant une rotation du poignet.", "Bras", "curl_haltere"),
                new Exercice("Curl marteau", "Contractez les bras et pliez les coudes de façon à soulever les haltères jusqu’à vos deltoïdes.", "Bras", "curl_marteau"),
                new Exercice("Curl concentration", "Monter la charge avec les biceps en gardant le dos droit", "Bras", "curl_concentration"),
                new Exercice("Extensions", "Descendre l'haltère derrière la tête en gardant les coudes pointés vers le plafond et le dos droit.", "Bras", "extension"),
                new Exercice("Kick back", "Tendez votre bras vers l'arrière en gardant le dos droit puis revenez à la position de départ.", "Bras", "kickback"),
                new Exercice("Développé couché prise serrée", "Descendre la charge lentement jusqu'à frôler les pectoraux et revenir à la position initiale.", "Bras", "developpe_coucher_barre"),

                new Exercice("Squats", "Fléchir les genoux et pousser les fesses en arrière avec le buste droit en gardant la barre sur les trapèzes. ", "Jambes", "squat"),
                new Exercice("Squats une jambe", "Fléchir le genou et amener la cuisse parallèle au sol en gardant le dos droit.", "Jambes", "squat_une_jambe"),
                new Exercice("Fentes", "Le genou arrière ne touche pas le sol, prenez appui sur votre talon et poussez pour revenir à la position de départ.", "Jambes", "fente"),
                new Exercice("Presse", "Décoller la charge jusqu'à ce que vos jambes soient presque tendues mais sans les tendre complètement.", "Jambes", "presse"),
                new Exercice("Soulevé de terre", "Lever la barre jusqu'au niveau des genoux à l’aide des quadriceps puis redresser le buste. Gardez le dos droit", "Jambes", "souleve_terre"),
                new Exercice("Mollets", "Se mettre au bord d'un step puis monter et descendre les chevilles", "Jambes", "mollet")

        };
    }

    @NonNull
    @Override
    public String toString() {
        String out = this.nom;
        return out;
    }
}
