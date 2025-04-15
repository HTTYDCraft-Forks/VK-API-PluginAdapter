package com.httydcraft.vk.api.core.api.parsers.objects;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a VK callback button event.
 */
public class CallbackButtonEvent {

    // #region Fields
    @SerializedName("user_id")
    private Integer userID;

    @SerializedName("peer_id")
    private Integer peerID;

    @SerializedName("event_id")
    private String eventID;

    @SerializedName("payload")
    private String payload;

    @SerializedName("conversation_message_id")
    private Integer conversationMessageID;

    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    // #endregion

    // #region Getters and Setters
    /**
     * Gets the user ID.
     * @return userID
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * Sets the user ID.
     * @param userID user ID
     * @return this
     */
    public CallbackButtonEvent setUserID(Integer userID) {
        Preconditions.checkNotNull(userID, "userID cannot be null");
        this.userID = userID;
        logger.atFine().log("Set userID: %d", userID);
        return this;
    }

    /**
     * Gets the peer ID.
     * @return peerID
     */
    public Integer getPeerID() {
        return peerID;
    }

    /**
     * Sets the peer ID.
     * @param peerID peer ID
     * @return this
     */
    public CallbackButtonEvent setPeerID(Integer peerID) {
        Preconditions.checkNotNull(peerID, "peerID cannot be null");
        this.peerID = peerID;
        logger.atFine().log("Set peerID: %d", peerID);
        return this;
    }

    /**
     * Gets the event ID.
     * @return eventID
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Sets the event ID.
     * @param eventID event ID
     * @return this
     */
    public CallbackButtonEvent setEventID(String eventID) {
        Preconditions.checkNotNull(eventID, "eventID cannot be null");
        this.eventID = eventID;
        logger.atFine().log("Set eventID: %s", eventID);
        return this;
    }

    /**
     * Gets the payload.
     * @return payload
     */
    public String getPayload() {
        return payload;
    }

    /**
     * Sets the payload.
     * @param payload event payload
     * @return this
     */
    public CallbackButtonEvent setPayload(String payload) {
        Preconditions.checkNotNull(payload, "payload cannot be null");
        this.payload = payload;
        logger.atFine().log("Set payload: %s", payload);
        return this;
    }

    /**
     * Gets the conversation message ID.
     * @return conversationMessageID
     */
    public Integer getConversationMessageID() {
        return conversationMessageID;
    }

    /**
     * Sets the conversation message ID.
     * @param conversationMessageID conversation message ID
     * @return this
     */
    public CallbackButtonEvent setConversationMessageID(Integer conversationMessageID) {
        Preconditions.checkNotNull(conversationMessageID, "conversationMessageID cannot be null");
        this.conversationMessageID = conversationMessageID;
        logger.atFine().log("Set conversationMessageID: %d", conversationMessageID);
        return this;
    }
    // #endregion

    // #region Object overrides
    @Override
    public int hashCode() {
        return java.util.Objects.hash(userID, peerID, eventID, payload, conversationMessageID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallbackButtonEvent that = (CallbackButtonEvent) o;
        return java.util.Objects.equals(userID, that.userID) &&
                java.util.Objects.equals(peerID, that.peerID) &&
                java.util.Objects.equals(eventID, that.eventID) &&
                java.util.Objects.equals(payload, that.payload) &&
                java.util.Objects.equals(conversationMessageID, that.conversationMessageID);
    }

    @Override
    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder("CallbackButtonEvent{");
        sb.append("userID=").append(userID);
        sb.append(", peerID=").append(peerID);
        sb.append(", eventID='").append(eventID).append("'");
        sb.append(", payload='").append(payload).append("'");
        sb.append(", conversationMessageID=").append(conversationMessageID);
        sb.append('}');
        return sb.toString();
    }
    // #endregion
}
