/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.example.data.MemberRepository;
import org.jboss.arquillian.example.model.Member;
import org.jboss.arquillian.example.service.MemberRegistration;
import org.jboss.arquillian.example.util.Resources;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ArquillianPersistenceTestCase {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(MemberRepository.class, Member.class, MemberRegistration.class, Resources.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private MemberRegistration registrationService;

    @Inject
    private MemberRepository memberRepository;

    @Test
    @Transactional(value = TransactionMode.ROLLBACK)
    public void registerMember() {
        assertNotNull(memberRepository);
        assertNotNull(registrationService);
        Member newMember = createMemberInstance("member", "member@test.com", "1234567890");
        registrationService.register(newMember);
        Member foundMember = memberRepository.findByEmail(newMember.getEmail());
        assertNotNull(foundMember);
        assertEquals(newMember.getName(), foundMember.getName());
        assertEquals(newMember.getEmail(), foundMember.getEmail());
        assertEquals(newMember.getPhoneNumber(), foundMember.getPhoneNumber());
    }

    @Test
    @UsingDataSet("datasets/members.yml")
    @Transactional(value = TransactionMode.DISABLED)
    public void findAllMembers() {
        assertNotNull(memberRepository);
        List<Member> members = memberRepository.findAllOrderedByName();
        assertNotNull("There are registered members", members);
        assertEquals("There are two registered members", 2, members.size());
        assertEquals("First Member is John", "John Smith", members.get(0).getName());
        assertEquals("Second Member is Kelly", "Kelly Smith", members.get(1).getName());
    }

    /*@Test
    @ShouldMatchDataSet("datasets/members.yml")
    @Transactional(value = TransactionMode.COMMIT)
    public void registerMembers() {
        assertNotNull(memberRepository);
        assertNotNull(registrationService);
        Member newMember = createMemberInstance("John Smith", "johnsmith@mailinator.com", "1234567890");
        registrationService.register(newMember);
        newMember = createMemberInstance("Kelly Smith", "kellysmith@mailinator.com", "1234567890");
        registrationService.register(newMember);
    }*/

    private Member createMemberInstance(String name, String email, String phone) {
        Member member = new Member();
        member.setEmail(email);
        member.setName(name);
        member.setPhoneNumber(phone);
        return member;
    }
}
