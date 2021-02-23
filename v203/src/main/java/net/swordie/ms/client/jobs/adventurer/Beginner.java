package net.swordie.ms.client.jobs.adventurer;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.loaders.SkillData;
import org.apache.log4j.LogManager;
import org.apache.log4j.LogManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 12/14/2017.
 */
public class Beginner extends Job {

    public static final int D_DASH = 80001417;
    public static final int S_JUMP = 80001416;
    private int[] addedSkills = new int[] {
            D_DASH,
            S_JUMP,
    };

    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    public Beginner(Char chr) {
        super(chr);

        if (chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                log.info(String.format("adding skill id %d", id));
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setRootId(1);
                    skill.setMasterLevel(1);
                    skill.setMaxLevel(1);
                    chr.addSkill(skill);
                }
            }
        }
    }

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        super.handleAttack(c, attackInfo);
    }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        super.handleSkill(c, skillID, slv, inPacket);
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById(id);
        return job == JobConstants.JobEnum.BEGINNER;
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    @Override
    public boolean isBuff(int skillID) {
        return super.isBuff(skillID);
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        if (chr.getSubJob() == 1) {
            cs.setPosMap(103050900);
        } else if (chr.getSubJob() == 2) {
            cs.setPosMap(3000600);
        } else {
            cs.setPosMap(4000011);
        }
    }
}
