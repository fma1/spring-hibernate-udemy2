package org.hibernatewrapper

import org.hibernate.{Session, SessionFactory}
import org.slf4j.LoggerFactory

import scala.language.implicitConversions

trait PreBoundSession extends ManagedSession {

  private val logger = LoggerFactory.getLogger(classOf[PreBoundSession])

  override abstract def create(): Session = {
    PreBoundSession.threadLocal.get() match {
      case Some(session) => session
      //delegate to super to create session
      case None => bindSession(super.create())
    }
  }

  override abstract def close(session: Session): Unit = {
    //do nothing for pre-bound session
  }

  def bindSession(session: Session) : Session = {
    logger.debug("bind session")
    PreBoundSession.threadLocal.set(Some(session))
    session
  }

  def unbindSession(): Unit =  {
    logger.debug("unbind session")
    val session = PreBoundSession.threadLocal.get()
    session.foreach { s => s.close() }
    PreBoundSession.threadLocal.set(None)
  }

}

object PreBoundSession {

  private val threadLocal: ThreadLocal[Option[Session]] = new java.lang.ThreadLocal[Option[Session]] {
    override def initialValue(): Option[Session] = None
  }

  implicit def sfWrapper(sf: SessionFactory) : SessionFactoryWrapper with PreBoundSession  =
    new SessionFactoryWrapper(sf) with PreBoundSession

}
